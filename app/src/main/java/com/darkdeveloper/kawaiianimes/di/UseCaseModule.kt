package com.darkdeveloper.kawaiianimes.di

import com.darkdeveloper.kawaiianimes.domain.usecase.anime.*
import com.darkdeveloper.kawaiianimes.domain.usecase.episode.*
import com.darkdeveloper.kawaiianimes.domain.usecase.player.*
import org.koin.dsl.module

val useCaseModule = module {
    // Anime
    factory { GetAnimeByIdUseCase(get()) }
    factory { GetPopularAnimesUseCase(get()) }
    factory { GetNewAnimesUseCase(get()) }
    factory { GetRecentAnimesUseCase(get()) }
    factory { GetRecommendationsUseCase(get()) }
    factory { GetRelatedAnimesUseCase(get()) }
    factory { GetAnimesByGenreUseCase(get()) }
    factory { GetAnimesByStatusUseCase(get()) }
    factory { GetAnimesByTypeUseCase(get()) }
    factory { GetMoviesUseCase(get()) }
    factory { GetCalendarUseCase(get()) }
    factory { GetWithCastellanoUseCase(get()) }
    factory { GetWithLatinoUseCase(get()) }
    factory { GetMostViewedUseCase(get()) }
    factory { SearchAnimesUseCase(get()) }
    factory { SearchAdvancedUseCase(get()) }
    factory { SearchWithFiltersUseCase(get()) }
    factory { SetViewsAnimeUseCase(get()) }
    factory { GetLanguagesForAnimeUseCase(get()) }

    // Episode
    factory { GetEpisodesForAnimeUseCase(get()) }
    factory { GetEpisodeUseCase(get()) }
    factory { GetNextEpisodeUseCase(get()) }
    factory { GetPreviousEpisodeUseCase(get()) }
    factory { GetLanguagesForEpisodeUseCase(get()) }

    // Player
    factory { GetServersUseCase(get()) }
    factory { GetServersForPlayerUseCase(get()) }
    factory { GetPlayersForEpisodeUseCase(get()) }
    factory { GetAvailableLanguagesByAnimeNumberUseCase(get()) }
    factory { BuildPlayerUrlUseCase(get(), get()) }
}