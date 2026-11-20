package com.darkdeveloper.kawaiianimes.domain.usecase.anime

import com.darkdeveloper.kawaiianimes.domain.model.Anime
import com.darkdeveloper.kawaiianimes.domain.repository.AnimeRepository
import javax.inject.Inject

class GetAnimesByGenreUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(genre: String, page: Int, limit: Int) = repository.getAnimesByGenre(genre, page, limit)
}