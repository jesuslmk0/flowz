package com.darkdeveloper.kawaiianimes.domain.usecase.anime

import com.darkdeveloper.kawaiianimes.domain.model.Anime
import com.darkdeveloper.kawaiianimes.domain.repository.AnimeRepository
import javax.inject.Inject

class GetAnimesByStatusUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(status: String, page: Int, limit: Int) = repository.getAnimesByStatus(status, page, limit)
}