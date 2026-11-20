package com.darkdeveloper.kawaiianimes.domain.usecase.player

import com.darkdeveloper.kawaiianimes.data.extractors.ExtractResult
import com.darkdeveloper.kawaiianimes.data.extractors.ExtractorRegistry
import com.darkdeveloper.kawaiianimes.domain.model.Server
import com.darkdeveloper.kawaiianimes.domain.repository.PlayerRepository
import javax.inject.Inject

class BuildPlayerUrlUseCase @Inject constructor(
    private val playerRepository: PlayerRepository,
    private val extractorRegistry: ExtractorRegistry
) {

    operator fun invoke(server: Server, episode: Int, language: String) = playerRepository.buildPlayerUrl(server, episode, language)

    suspend fun execute(server: Server, episode: Int, language: String): ExtractResult {
        return playerRepository.buildPlayerUrl(server, episode, language).fold(
            onSuccess = { url ->
                ExtractResult.Success(url)
            },
            onFailure = { error ->
                ExtractResult.Failure(error.message ?: "Unknown error")
            }
        )
    }
}