package com.darkdeveloper.kawaiianimes.domain.usecase.player

import com.darkdeveloper.kawaiianimes.domain.model.Player
import com.darkdeveloper.kawaiianimes.domain.repository.PlayerRepository
import javax.inject.Inject

class GetPlayersForEpisodeUseCase @Inject constructor(
    private val repository: PlayerRepository
) {
    operator fun invoke(episodeId: String) = repository.getPlayersForEpisode(episodeId)
}