package com.darkdeveloper.kawaiianimes.domain.usecase.episode

import com.darkdeveloper.kawaiianimes.domain.repository.EpisodeRepository
import javax.inject.Inject

class GetLanguagesForEpisodeUseCase @Inject constructor(
    private val repository: EpisodeRepository
) {
    operator fun invoke(episodeId: String) = repository.getLanguagesForEpisode(episodeId)
}