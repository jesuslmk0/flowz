package com.darkdeveloper.kawaiianimes.domain.usecase.anime

import com.darkdeveloper.kawaiianimes.domain.model.Anime
import com.darkdeveloper.kawaiianimes.domain.repository.AnimeRepository
import javax.inject.Inject

class GetRelatedAnimesUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(animeId: String, page: Int, limit: Int) = repository.getRelatedAnimes(animeId, page, limit)
}