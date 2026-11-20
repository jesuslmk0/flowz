package com.darkdeveloper.kawaiianimes.data.extractors

import io.ktor.client.HttpClient
import io.ktor.http.HttpHeaders

class GenericIframeExtractor(httpClient: HttpClient) : BaseVideoExtractor(httpClient) {

    override val supportedHosts = emptyList()

    override fun supports(embedUrl: String): Boolean = true

    override protected fun parsePlayableUrl(html: String, embedUrl: String): ExtractResult {
        val iframeRegex = """<iframe[^>]+src\s*=\s*["']([^"']+)["']""".toRegex(RegexOption.IGNORE_CASE)
        val iframes = iframeRegex.findAll(html).map { it.groupValues[1] }.toList()

        for (iframeSrc in iframes) {
            if (iframeSrc.contains("http")) {
                // Could recursively extract from iframe, but CORS may block
            }
        }

        findM3u8InHtml(html)?.let {
            return ExtractResult.Success(it, mapOf(HttpHeaders.Referer to embedUrl))
        }

        val mp4Regex = """https?://[^"'\s]+\.mp4[^"'\s]*""".toRegex()
        mp4Regex.find(html)?.value?.let {
            return ExtractResult.Success(it, mapOf(HttpHeaders.Referer to embedUrl))
        }

        val jsonLdRegex = """<script[^>]+type=["']application/ld\+json["'][^>]*>(.+?)</script>""".toRegex(RegexOption.DOT_MATCHES_ALL)
        jsonLdRegex.find(html)?.groupValues?.get(1)?.let { json ->
            findM3u8InJson(json)?.let {
                return ExtractResult.Success(it, mapOf(HttpHeaders.Referer to embedUrl))
            }
        }

        return ExtractResult.Failure("Generic: No playable URL found", retryable = false)
    }

    private fun findM3u8InJson(json: String): String? {
        val m3u8Regex = """https?://[^"'\s]+\.m3u8[^"'\s]*""".toRegex()
        return m3u8Regex.find(json)?.value
    }
}