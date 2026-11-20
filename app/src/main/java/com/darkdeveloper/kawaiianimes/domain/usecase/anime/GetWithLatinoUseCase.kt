package com.darkdeveloper.kawaiianimes.domain.usecase.anime

import com.darkdeveloper.kawaiianimes.domain.model.Anime
import com.darkdeveloper.kawaiianimes.domain.repository.AnimeRepository
import javax.inject.Inject

class GetWithLatinoUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(page: Int, limit: Int) = repository.getWithLatino(page, limit)
}