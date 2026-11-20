package com.darkdeveloper.kawaiianimes.domain.usecase.anime

import com.darkdeveloper.kawaiianimes.domain.model.Language
import com.darkdeveloper.kawaiianimes.domain.repository.AnimeRepository
import javax.inject.Inject

class GetLanguagesForAnimeUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(animeId: String) = repository.getLanguagesForAnime(animeId)
}