package com.darkdeveloper.kawaiianimes.domain.usecase.anime

import com.darkdeveloper.kawaiianimes.domain.model.Anime
import com.darkdeveloper.kawaiianimes.domain.repository.AnimeRepository
import com.darkdeveloper.kawaiianimes.domain.repository.SearchFilters
import javax.inject.Inject

class SearchAdvancedUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(
        query: String,
        genres: List<String>?,
        status: String?,
        type: String?,
        year: Int?,
        page: Int,
        limit: Int
    ) = repository.searchAdvanced(query, genres, status, type, year, page, limit)
}