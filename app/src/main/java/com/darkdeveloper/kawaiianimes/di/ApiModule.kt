package com.darkdeveloper.kawaiianimes.di

import com.darkdeveloper.kawaiianimes.data.api.KawaiiApiService
import com.darkdeveloper.kawaiianimes.data.api.kotlinxSerializationJson
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.DefaultRequest
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module
import java.time.Duration

val apiModule = module {
    single<HttpClient> {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(kotlinxSerializationJson)
            }
            install(Logging) {
                level = LogLevel.HEADERS
            }
            defaultRequest {
                url("https://api.kawaiianimes.app/api/v4")
                header(HttpHeaders.UserAgent, "KawaiiAnimes/1.0.0 (Android)")
                header(HttpHeaders.Accept, "application/json")
                timeout(Duration.ofSeconds(30))
            }
        }
    }

    single { KawaiiApiService(get()) }
}