package com.darkdeveloper.kawaiianimes.di

import com.darkdeveloper.kawaiianimes.data.repository.AnimeRepositoryImpl
import com.darkdeveloper.kawaiianimes.data.repository.AuthRepositoryImpl
import com.darkdeveloper.kawaiianimes.data.repository.EpisodeRepositoryImpl
import com.darkdeveloper.kawaiianimes.data.repository.PlayerRepositoryImpl
import com.darkdeveloper.kawaiianimes.domain.repository.AnimeRepository
import com.darkdeveloper.kawaiianimes.domain.repository.AuthRepository
import com.darkdeveloper.kawaiianimes.domain.repository.EpisodeRepository
import com.darkdeveloper.kawaiianimes.domain.repository.PlayerRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AnimeRepository> { AnimeRepositoryImpl(get(), get()) }
    single<EpisodeRepository> { EpisodeRepositoryImpl(get(), get()) }
    single<PlayerRepository> { PlayerRepositoryImpl(get(), get(), get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
}