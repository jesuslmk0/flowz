package com.darkdeveloper.kawaiianimes.domain.usecase.player

import com.darkdeveloper.kawaiianimes.domain.repository.PlayerRepository
import javax.inject.Inject

class GetAvailableLanguagesByAnimeNumberUseCase @Inject constructor(
    private val repository: PlayerRepository
) {
    operator fun invoke(animeNumber: Int) = repository.getAvailableLanguagesByAnimeNumber(animeNumber)
}