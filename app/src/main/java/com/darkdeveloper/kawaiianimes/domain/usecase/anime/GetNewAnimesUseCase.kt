package com.darkdeveloper.kawaiianimes.domain.usecase.anime

import com.darkdeveloper.kawaiianimes.domain.model.Anime
import com.darkdeveloper.kawaiianimes.domain.repository.AnimeRepository
import javax.inject.Inject

class GetNewAnimesUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(page: Int, limit: Int) = repository.getNewAnimes(page, limit)
}