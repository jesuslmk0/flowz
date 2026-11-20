package com.darkdeveloper.kawaiianimes.data.api.dto

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeDto(
    val id: String,
    val animeId: String,
    val number: Int,
    val title: String?,
    val overview: String?,
    val aired: String?,
    val duration: Int?,
    val thumbnail: String?,
    val filler: Boolean,
    val languages: List<LanguageDto>,
    val servers: List<ServerDto>?
) {
    private val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    fun toDomain(): Episode = Episode(
        id = id,
        animeId = animeId,
        number = number,
        title = title,
        overview = overview,
        aired = aired?.let { LocalDateTime.parse(it, dateTimeFormatter) },
        duration = duration,
        thumbnail = thumbnail,
        filler = filler,
        languages = languages.map { it.toDomain() },
        servers = servers?.map { it.toDomain() } ?: emptyList()
    )
}