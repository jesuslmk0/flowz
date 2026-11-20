package com.darkdeveloper.kawaiianimes.domain.usecase.anime

import com.darkdeveloper.kawaiianimes.domain.model.Anime
import com.darkdeveloper.kawaiianimes.domain.repository.AnimeRepository
import com.darkdeveloper.kawaiianimes.domain.repository.SearchFilters
import javax.inject.Inject

class SearchAnimesUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(query: String, page: Int, limit: Int) = repository.searchAnimes(query, page, limit)
}