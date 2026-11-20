package com.darkdeveloper.kawaiianimes.data.extractors

import io.ktor.client.HttpClient
import io.ktor.http.HttpHeaders

class UpstreamExtractor(httpClient: HttpClient) : BaseVideoExtractor(httpClient) {

    override val supportedHosts = listOf("upstream.to", "upstreamcdn.to", "upstreamvip.to")

    override protected fun parsePlayableUrl(html: String, embedUrl: String): ExtractResult {
        findVideoUrlInHtml(html)?.let {
            return ExtractResult.Success(it, mapOf(HttpHeaders.Referer to embedUrl))
        }
        findM3u8InHtml(html)?.let {
            return ExtractResult.Success(it, mapOf(HttpHeaders.Referer to embedUrl))
        }

        return ExtractResult.Failure("Upstream: No playable URL found", retryable = true)
    }
}