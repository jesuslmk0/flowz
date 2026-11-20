package com.darkdeveloper.kawaiianimes.data.extractors

import io.ktor.client.HttpClient
import io.ktor.http.HttpHeaders

class FilemoonExtractor(httpClient: HttpClient) : BaseVideoExtractor(httpClient) {

    override val supportedHosts = listOf(
        "filemoon.to", "filemoon.sx", "filemoon.in", "filemoon.net",
        "filemoon.link", "filemoon.online"
    )

    override protected fun parsePlayableUrl(html: String, embedUrl: String): ExtractResult {
        val configPatterns = listOf(
            """player_config\s*=\s*({.+?})""".toRegex(RegexOption.DOT_MATCHES_ALL),
            """sources\s*:\s*(\[.+?\])""".toRegex(RegexOption.DOT_MATCHES_ALL),
            """file\s*:\s*["']([^"']+\.m3u8[^"']*)["']""".toRegex(RegexOption.IGNORE_CASE)
        )

        for (pattern in configPatterns) {
            pattern.find(html)?.groupValues?.get(1)?.let { jsonStr ->
                findM3u8InJson(jsonStr)?.let {
                    return ExtractResult.Success(it, mapOf(HttpHeaders.Referer to embedUrl))
                }
            }
        }

        findVideoUrlInHtml(html)?.let {
            return ExtractResult.Success(it, mapOf(HttpHeaders.Referer to embedUrl))
        }
        findM3u8InHtml(html)?.let {
            return ExtractResult.Success(it, mapOf(HttpHeaders.Referer to embedUrl))
        }

        return ExtractResult.Failure("Filemoon: No playable URL found", retryable = true)
    }

    private fun findM3u8InJson(json: String): String? {
        val m3u8Regex = """https?://[^"'\s]+\.m3u8[^"'\s]*""".toRegex()
        return m3u8Regex.find(json)?.value
    }
}