package com.darkdeveloper.kawaiianimes.data.api.dto

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlinx.serialization.Serializable

@Serializable
data class AnimeDto(
    val id: String,
    val name: String,
    val nameAlternative: String?,
    val poster: String,
    val banner: String?,
    val overview: String?,
    val aired: String?,
    val type: AnimeType,
    val status: AnimeStatus,
    val genres: List<String>,
    val rating: Double,
    val voteAverage: Double,
    val season: String?,
    val broadcast: String?,
    val prequel: String?,
    val sequel: String?,
    val related: List<String>?,
    val views: Long,
    val updatedAt: Long,
    val languages: List<LanguageDto>?
) {
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    fun toDomain(): Anime = Anime(
        id = id,
        name = name,
        nameAlternative = nameAlternative,
        poster = poster,
        banner = banner,
        overview = overview,
        aired = aired?.let { LocalDate.parse(it, dateFormatter) },
        type = type,
        status = status,
        genres = genres,
        rating = rating,
        voteAverage = voteAverage,
        season = season,
        broadcast = broadcast,
        prequel = prequel,
        sequel = sequel,
        related = related ?: emptyList(),
        views = views,
        updatedAt = updatedAt,
        languages = languages?.map { it.toDomain() } ?: emptyList()
    )
}

@Serializable
enum class AnimeType { TV, MOVIE, OVA, SPECIAL, ONA, MUSIC }

@Serializable
enum class AnimeStatus { ONGOING, COMPLETED, UPCOMING, HIATUS }

@Serializable
data class LanguageDto(
    val code: String,
    val name: String,
    val nativeName: String
) {
    fun toDomain() = Language(code, name, nativeName)
}