package com.darkdeveloper.kawaiianimes.data.extractors

import android.util.Log
import com.darkdeveloper.kawaiianimes.domain.model.Server
import kotlinx.coroutines.flow.MutableStateFlow

private const val TAG = "ExtractorRegistry"

class ExtractorRegistry(
    private val extractors: List<VideoExtractor>
) {

    private val _extractionStats = MutableStateFlow<Map<String, ExtractionStats>>(emptyMap())
    val extractionStats = _extractionStats

    suspend fun extract(server: Server): ExtractResult {
        val extractor = extractors.firstOrNull { it.supports(server.embedUrl) }
            ?: return ExtractResult.Failure("No extractor available for: ${server.embedUrl}").also {
                Log.w(TAG, "No extractor for URL: ${server.embedUrl} | Server: ${server.name} (${server.type})")
            }

        val extractorName = extractor.javaClass.simpleName
        Log.d(TAG, "Trying $extractorName for: ${server.embedUrl}")
        val result = extractor.extract(server.embedUrl, server.referer)
        recordStats(extractorName, result)
        when (result) {
            is ExtractResult.Success -> Log.i(TAG, "$extractorName SUCCESS: ${result.playableUrl}")
            is ExtractResult.Failure -> Log.w(TAG, "$extractorName FAILED: ${result.error} (retryable=${result.retryable})")
        }
        return result
    }

    suspend fun extractWithFallback(server: Server): ExtractResult {
        val primary = extract(server)
        if (primary is ExtractResult.Success) return primary

        Log.i(TAG, "Primary extractor failed, trying GenericIframeExtractor fallback for: ${server.embedUrl}")
        val generic = extractors.find { it is GenericIframeExtractor }
        if (generic != null) {
            val result = generic.extract(server.embedUrl, server.referer)
            recordStats("GenericIframeExtractor", result)
            when (result) {
                is ExtractResult.Success -> Log.i(TAG, "Fallback SUCCESS: ${result.playableUrl}")
                is ExtractResult.Failure -> Log.e(TAG, "Fallback FAILED: ${result.error} — giving up")
            }
            return result
        }
        Log.e(TAG, "No fallback available, returning primary failure")
        return primary
    }

    private fun recordStats(extractorName: String, result: ExtractResult) {
        _extractionStats.value = _extractionStats.value.let { current ->
            val stats = current[extractorName] ?: ExtractionStats(extractorName)
            current + (extractorName to stats.copy(
                attempts = stats.attempts + 1,
                successes = stats.successes + (if (result is ExtractResult.Success) 1 else 0),
                lastAttempt = System.currentTimeMillis(),
                lastError = (result as? ExtractResult.Failure)?.error
            ))
        }
    }

    data class ExtractionStats(
        val name: String,
        var attempts: Int = 0,
        var successes: Int = 0,
        var lastAttempt: Long = 0,
        var lastError: String? = null
    ) {
        val successRate: Float = if (attempts > 0) successes.toFloat() / attempts else 0f
    }
}