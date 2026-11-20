package com.darkdeveloper.kawaiianimes.domain.model

import java.time.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Anime(
    val id: String,
    val name: String,
    val nameAlternative: String?,
    val poster: String,
    val banner: String?,
    val overview: String?,
    val aired: LocalDate?,
    val type: AnimeType,
    val status: AnimeStatus,
    val genres: List<String>,
    val rating: Double,
    val voteAverage: Double,
    val season: String?,
    val broadcast: String?,
    val prequel: String?,
    val sequel: String?,
    val related: List<String>,
    val views: Long,
    val updatedAt: Long,
    val languages: List<Language>,
    val isFavorite: Boolean = false,
    val isWatchLater: Boolean = false,
    val lastViewedAt: Long = 0
) {
    val displayName: String = nameAlternative?.takeIf { it.isNotBlank() } ?: name
    val primaryGenre: String = genres.firstOrNull() ?: "Desconocido"
}

@Serializable
enum class AnimeType { TV, MOVIE, OVA, SPECIAL, ONA, MUSIC }

@Serializable
enum class AnimeStatus { ONGOING, COMPLETED, UPCOMING, HIATUS }

@Serializable
data class Language(
    val code: String,
    val name: String,
    val nativeName: String
) {
    val displayName: String = nativeName.takeIf { it.isNotBlank() } ?: name
}