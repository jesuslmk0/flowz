package com.darkdeveloper.kawaiianimes.di

import com.darkdeveloper.kawaiianimes.presentation.home.HomeViewModel
import com.darkdeveloper.kawaiianimes.presentation.search.SearchViewModel
import com.darkdeveloper.kawaiianimes.presentation.anime.AnimeDetailViewModel
import com.darkdeveloper.kawaiianimes.presentation.anime.EpisodeListViewModel
import com.darkdeveloper.kawaiianimes.presentation.player.PlayerViewModel
import com.darkdeveloper.kawaiianimes.presentation.profile.ProfileViewModel
import com.darkdeveloper.kawaiianimes.presentation.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get(), get(), get(), get(), get()) }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { AnimeDetailViewModel(get(), get(), get()) }
    viewModel { EpisodeListViewModel(get()) }
    viewModel { PlayerViewModel(get(), get(), get(), get()) }
    viewModel { ProfileViewModel(get(), get()) }
    viewModel { SettingsViewModel() }
}