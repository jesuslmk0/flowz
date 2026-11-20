package com.darkdeveloper.kawaiianimes.data.extractors

import io.ktor.client.HttpClient
import io.ktor.http.HttpHeaders

class Mp4UploadExtractor(httpClient: HttpClient) : BaseVideoExtractor(httpClient) {

    override val supportedHosts = listOf(
        "mp4upload.com", "www.mp4upload.com"
    )

    override protected fun parsePlayableUrl(html: String, embedUrl: String): ExtractResult {
        val videoTagRegex = """<video[^>]+src\s*=\s*["']([^"']+)["']""".toRegex(RegexOption.IGNORE_CASE)
        videoTagRegex.find(html)?.groupValues?.get(1)?.let {
            return ExtractResult.Success(it, mapOf(HttpHeaders.Referer to embedUrl))
        }

        val sourceRegex = """<source[^>]+src\s*=\s*["']([^"']+)["']""".toRegex(RegexOption.IGNORE_CASE)
        sourceRegex.find(html)?.groupValues?.get(1)?.let {
            return ExtractResult.Success(it, mapOf(HttpHeaders.Referer to embedUrl))
        }

        findVideoUrlInHtml(html)?.let {
            return ExtractResult.Success(it, mapOf(HttpHeaders.Referer to embedUrl))
        }
        findM3u8InHtml(html)?.let {
            return ExtractResult.Success(it, mapOf(HttpHeaders.Referer to embedUrl))
        }

        val mp4Regex = """https?://[^"'\s]+\.mp4[^"'\s]*""".toRegex()
        mp4Regex.find(html)?.value?.let {
            return ExtractResult.Success(it, mapOf(HttpHeaders.Referer to embedUrl))
        }

        return ExtractResult.Failure("Mp4Upload: No video found", retryable = true)
    }
}