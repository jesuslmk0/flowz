package com.darkdeveloper.kawaiianimes.di

import com.darkdeveloper.kawaiianimes.data.extractors.*
import io.ktor.client.HttpClient
import org.koin.dsl.module

val extractorModule = module {
    single<VideoExtractor> { StreamWishExtractor(get<HttpClient>()) }
    single<VideoExtractor> { FilemoonExtractor(get<HttpClient>()) }
    single<VideoExtractor> { Mp4UploadExtractor(get<HttpClient>()) }
    single<VideoExtractor> { VoeExtractor(get<HttpClient>()) }
    single<VideoExtractor> { UpstreamExtractor(get<HttpClient>()) }
    single<VideoExtractor> { GenericIframeExtractor(get<HttpClient>()) }

    single<ExtractorRegistry> {
        ExtractorRegistry(listOf(
            get<StreamWishExtractor>(),
            get<FilemoonExtractor>(),
            get<Mp4UploadExtractor>(),
            get<VoeExtractor>(),
            get<UpstreamExtractor>(),
            get<GenericIframeExtractor>()
        ))
    }
}