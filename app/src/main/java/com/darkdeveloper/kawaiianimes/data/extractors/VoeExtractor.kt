package com.darkdeveloper.kawaiianimes.data.extractors

import io.ktor.client.HttpClient
import io.ktor.http.HttpHeaders

class VoeExtractor(httpClient: HttpClient) : BaseVideoExtractor(httpClient) {

    override val supportedHosts = listOf("voe.sx", "voecdn.net", "voe.sx")

    override protected fun parsePlayableUrl(html: String, embedUrl: String): ExtractResult {
        val sourcesRegex = """sources\s*:\s*(\[.+?\])""".toRegex(RegexOption.DOT_MATCHES_ALL)
        sourcesRegex.find(html)?.groupValues?.get(1)?.let { sourcesJson ->
            findM3u8InJson(sourcesJson)?.let {
                return ExtractResult.Success(it, mapOf(HttpHeaders.Referer to embedUrl))
            }
        }

        findVideoUrlInHtml(html)?.let {
            return ExtractResult.Success(it, mapOf(HttpHeaders.Referer to embedUrl))
        }
        findM3u8InHtml(html)?.let {
            return ExtractResult.Success(it, mapOf(HttpHeaders.Referer to embedUrl))
        }

        return ExtractResult.Failure("Voe: No playable URL found", retryable = true)
    }

    private fun findM3u8InJson(json: String): String? {
        val m3u8Regex = """https?://[^"'\s]+\.m3u8[^"'\s]*""".toRegex()
        return m3u8Regex.find(json)?.value
    }
}