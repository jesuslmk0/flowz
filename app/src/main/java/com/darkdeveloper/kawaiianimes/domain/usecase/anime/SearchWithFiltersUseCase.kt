package com.darkdeveloper.kawaiianimes.domain.usecase.anime

import com.darkdeveloper.kawaiianimes.domain.model.Anime
import com.darkdeveloper.kawaiianimes.domain.repository.AnimeRepository
import com.darkdeveloper.kawaiianimes.domain.repository.SearchFilters
import javax.inject.Inject

class SearchWithFiltersUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(filters: SearchFilters) = repository.searchWithFilters(filters)
}