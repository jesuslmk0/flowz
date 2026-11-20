package com.darkdeveloper.kawaiianimes.domain.usecase.anime

import com.darkdeveloper.kawaiianimes.domain.repository.AnimeRepository
import javax.inject.Inject

class SetViewsAnimeUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(animeId: String) = repository.setViewsAnime(animeId)
}