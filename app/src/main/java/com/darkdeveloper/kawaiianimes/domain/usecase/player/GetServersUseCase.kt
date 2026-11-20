package com.darkdeveloper.kawaiianimes.domain.usecase.player

import com.darkdeveloper.kawaiianimes.domain.model.Server
import com.darkdeveloper.kawaiianimes.domain.repository.PlayerRepository
import javax.inject.Inject

class GetServersUseCase @Inject constructor(
    private val repository: PlayerRepository
) {
    operator fun invoke(animeId: String, episode: Int, language: String) = repository.getServers(animeId, episode, language)
}