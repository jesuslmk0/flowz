package com.darkdeveloper.kawaiianimes.data.extractors

import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StreamWishExtractor(httpClient: HttpClient) : BaseVideoExtractor(httpClient) {

    override val supportedHosts = listOf(
        "streamwish.to", "streamwish.com", "swish.to", "swish.com",
        "streamwish.net", "streamwish.org"
    )

    override protected fun parsePlayableUrl(html: String, embedUrl: String): ExtractResult = withContext(Dispatchers.IO) {
        findVideoUrlInHtml(html)?.let {
            if (it.endsWith(".m3u8") || it.contains(".m3u8")) {
                return@withContext ExtractResult.Success(it, mapOf(HttpHeaders.Referer to embedUrl))
            }
        }

        findM3u8InHtml(html)?.let {
            return@withContext ExtractResult.Success(it, mapOf(HttpHeaders.Referer to embedUrl))
        }

        val jsPatterns = listOf(
            """player_data\s*=\s*({.+?})""".toRegex(RegexOption.DOT_MATCHES_ALL),
            """sources\s*:\s*(\[.+?\])""".toRegex(RegexOption.DOT_MATCHES_ALL),
            """file\s*:\s*["']([^"']+\.m3u8[^"']*)["']""".toRegex(RegexOption.IGNORE_CASE)
        )

        for (pattern in jsPatterns) {
            pattern.find(html)?.groupValues?.get(1)?.let { jsonStr ->
                val m3u8 = findM3u8InJson(jsonStr)
                if (m3u8 != null) {
                    return@withContext ExtractResult.Success(m3u8, mapOf(HttpHeaders.Referer to embedUrl))
                }
            }
        }

        ExtractResult.Failure("No m3u8 found in StreamWish page", retryable = true)
    }

    private fun findM3u8InJson(json: String): String? {
        val m3u8Regex = """https?://[^"'\s]+\.m3u8[^"'\s]*""".toRegex()
        return m3u8Regex.find(json)?.value
    }
}