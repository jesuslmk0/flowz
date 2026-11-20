package com.darkdeveloper.kawaiianimes.domain.usecase.episode

import com.darkdeveloper.kawaiianimes.domain.model.Episode
import com.darkdeveloper.kawaiianimes.domain.repository.EpisodeRepository
import javax.inject.Inject

class GetEpisodeUseCase @Inject constructor(
    private val repository: EpisodeRepository
) {
    operator fun invoke(episodeId: String) = repository.getEpisode(episodeId)
}