package com.darkdeveloper.kawaiianimes.domain.usecase.anime

import com.darkdeveloper.kawaiianimes.domain.model.Anime
import com.darkdeveloper.kawaiianimes.domain.repository.AnimeRepository
import javax.inject.Inject

class GetCalendarUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    operator fun invoke(season: String?, year: Int?) = repository.getCalendar(season, year)
}