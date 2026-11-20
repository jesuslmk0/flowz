package com.darkdeveloper.kawaiianimes.domain.usecase.anime

import com.darkdeveloper.kawaiianimes.domain.model.Anime
import com.darkdeveloper.kawaiianimes.domain.repository.AnimeRepository
import javax.inject.Inject

class GetAnimesByTypeUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(type: String, page: Int, limit: Int) = repository.getAnimesByType(type, page, limit)
}