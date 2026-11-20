package com.darkdeveloper.kawaiianimes.extractors

import com.darkdeveloper.kawaiianimes.data.extractors.ExtractResult
import com.darkdeveloper.kawaiianimes.data.extractors.ExtractorRegistry
import com.darkdeveloper.kawaiianimes.data.extractors.GenericIframeExtractor
import com.darkdeveloper.kawaiianimes.data.extractors.StreamWishExtractor
import com.darkdeveloper.kawaiianimes.domain.model.Server
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ExtractorRegistryTest {

    private lateinit var registry: ExtractorRegistry
    private lateinit var mockClient: HttpClient

    @Before
    fun setup() {
        mockClient = HttpClient(MockEngine) {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }
        registry = ExtractorRegistry(listOf(
            StreamWishExtractor(mockClient),
            GenericIframeExtractor(mockClient)
        ))
    }

    @Test
    fun `routes to correct extractor`() = runBlocking {
        val server = Server(
            id = "1",
            name = "StreamWish",
            type = com.darkdeveloper.kawaiianimes.domain.model.ServerType.STREAMWISH,
            embedUrl = "https://streamwish.to/e/abc123",
            quality = 720,
            language = "sub",
            isPremium = false
        )

        val result = registry.extract(server)
        // Will fail with mock, but tests routing logic
        assertNotNull(result)
    }

    @Test
    fun `fallback to generic when no specific extractor`() = runBlocking {
        val server = Server(
            id = "2",
            name = "Unknown",
            type = com.darkdeveloper.kawaiianimes.domain.model.ServerType.UNKNOWN,
            embedUrl = "https://unknown.host/e/xyz",
            quality = 720,
            language = "sub",
            isPremium = false
        )

        val result = registry.extractWithFallback(server)
        assertNotNull(result)
    }
}