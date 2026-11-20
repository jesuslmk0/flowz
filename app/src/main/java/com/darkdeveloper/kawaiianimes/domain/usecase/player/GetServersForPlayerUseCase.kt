package com.darkdeveloper.kawaiianimes.domain.usecase.player

import com.darkdeveloper.kawaiianimes.domain.model.Server
import com.darkdeveloper.kawaiianimes.domain.repository.PlayerRepository
import javax.inject.Inject

class GetServersForPlayerUseCase @Inject constructor(
    private val repository: PlayerRepository
) {
    operator fun invoke(playerId: String) = repository.getServersForPlayer(playerId)
}