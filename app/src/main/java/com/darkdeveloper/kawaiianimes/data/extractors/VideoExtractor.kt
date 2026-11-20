package com.darkdeveloper.kawaiianimes.data.extractors

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface VideoExtractor {

    val supportedHosts: List<String>

    fun supports(embedUrl: String): Boolean = supportedHosts.any { embedUrl.contains(it, ignoreCase = true) }

    suspend fun extract(embedUrl: String, referer: String?): ExtractResult

    val defaultHeaders: Map<String, String>
        get() = mapOf(
            HttpHeaders.UserAgent to "Mozilla/5.0 (Linux; Android 13; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36",
            HttpHeaders.Accept to "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8",
            HttpHeaders.AcceptLanguage to "es-ES,es;q=0.9,en;q=0.8",
            HttpHeaders.AcceptEncoding to "gzip, deflate, br",
            HttpHeaders.CacheControl to "no-cache",
            HttpHeaders.Pragma to "no-cache",
            "Sec-Fetch-Dest" to "document",
            "Sec-Fetch-Mode" to "navigate",
            "Sec-Fetch-Site" to "cross-site",
            "Sec-Fetch-User" to "?1",
            "Upgrade-Insecure-Requests" to "1",
            "Sec-CH-UA" to "\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Google Chrome\";v=\"120\"",
            "Sec-CH-UA-Mobile" to "?1",
            "Sec-CH-UA-Platform" to "\"Android\""
        )
}

sealed interface ExtractResult {
    data class Success(val playableUrl: String, val headers: Map<String, String> = emptyMap()) : ExtractResult
    data class Failure(val error: String, val retryable: Boolean = true) : ExtractResult
}

abstract class BaseVideoExtractor(
    protected val httpClient: HttpClient
) : VideoExtractor {

    override suspend fun extract(embedUrl: String, referer: String?): ExtractResult = withContext(Dispatchers.IO) {
        try {
            val headers = buildHeaders(referer)
            val html = httpClient.get<String>(embedUrl) {
                headers.forEach { (key, value) -> header(key, value) }
                followRedirects = true
            }
            parsePlayableUrl(html, embedUrl)
        } catch (e: Exception) {
            ExtractResult.Failure("Extractor error: ${e.message}", retryable = true)
        }
    }

    protected open fun buildHeaders(referer: String?): Map<String, String> =
        defaultHeaders + (referer?.let { "Referer" to it } ?: emptyMap())

    protected fun findVideoUrlInHtml(html: String): String? {
        val regex = """["']video_?url["']\s*:\s*["']([^"']+)["']""".toRegex(RegexOption.IGNORE_CASE)
        return regex.find(html)?.groupValues?.get(1)
    }

    protected fun findM3u8InHtml(html: String): String? {
        val patterns = listOf(
            """https?://[^"'\s]+\.m3u8[^"'\s]*""".toRegex(),
            """["'](https?://[^"'\s]+\.m3u8[^"'\s]*)["']""".toRegex(),
            """src\s*:\s*["'](https?://[^"'\s]+\.m3u8[^"'\s]*)["']""".toRegex(RegexOption.IGNORE_CASE),
            """file\s*:\s*["'](https?://[^"'\s]+\.m3u8[^"'\s]*)["']""".toRegex(RegexOption.IGNORE_CASE)
        )
        return patterns
            .mapNotNull { it.find(html)?.groupValues?.get(1) }
            .firstOrNull()
    }

    protected abstract fun parsePlayableUrl(html: String, embedUrl: String): ExtractResult
}