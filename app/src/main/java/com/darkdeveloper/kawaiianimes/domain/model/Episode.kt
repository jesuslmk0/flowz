package com.darkdeveloper.kawaiianimes.domain.model

import java.time.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Episode(
    val id: String,
    val animeId: String,
    val number: Int,
    val title: String?,
    val overview: String?,
    val aired: LocalDateTime?,
    val duration: Int?,
    val thumbnail: String?,
    val filler: Boolean,
    val languages: List<Language>,
    val servers: List<Server>
) {
    val displayTitle: String = title?.takeIf { it.isNotBlank() } ?: "Episodio $number"
    val formattedNumber: String = "%02d".format(number)
}