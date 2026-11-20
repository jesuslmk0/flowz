package com.darkdeveloper.kawaiianimes.extractors

import com.darkdeveloper.kawaiianimes.data.extractors.ExtractResult
import com.darkdeveloper.kawaiianimes.data.extractors.StreamWishExtractor
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class StreamWishExtractorTest {

    private lateinit var extractor: StreamWishExtractor
    private lateinit var mockClient: HttpClient

    @Before
    fun setup() {
        mockClient = HttpClient(MockEngine) {
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }
        extractor = StreamWishExtractor(mockClient)
    }

    @Test
    fun `supports StreamWish domains`() {
        assertTrue(extractor.supports("https://streamwish.to/e/abc123"))
        assertTrue(extractor.supports("https://streamwish.com/e/xyz789"))
        assertFalse(extractor.supports("https://filemoon.to/e/abc"))
    }

    @Test
    fun `extracts m3u8 from HTML with video_url`() = runBlocking {
        val html = """
            <html>
                <script>
                    var player_data = {"video_url": "https://cdn.streamwish.to/stream/abc123.m3u8?token=xyz"};
                </script>
            </html>
        """.trimIndent()

        mockClient.engine.mock {
            on { url { host = "streamwish.to" } } {
                respond(html)
            }
        }

        val result = extractor.extract("https://streamwish.to/e/abc123")
        assertTrue(result is ExtractResult.Success)
        assertEquals("https://cdn.streamwish.to/stream/abc123.m3u8?token=xyz", (result as ExtractResult.Success).playableUrl)
    }

    @Test
    fun `returns failure when no m3u8 found`() = runBlocking {
        val html = "<html><body>No video here</body></html>"

        mockClient.engine.mock {
            on { url { host = "streamwish.to" } } {
                respond(html)
            }
        }

        val result = extractor.extract("https://streamwish.to/e/abc123")
        assertTrue(result is ExtractResult.Failure)
    }
}