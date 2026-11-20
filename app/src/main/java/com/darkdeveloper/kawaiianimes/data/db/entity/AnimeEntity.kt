package com.darkdeveloper.kawaiianimes.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "anime", indices = [
    androidx.room.Index(value = ["name"]),
    androidx.room.Index(value = ["status"]),
    androidx.room.Index(value = ["type"]),
    androidx.room.Index(value = ["updatedAt"])
])
data class AnimeEntity(
    @PrimaryKey val id: String,
    val name: String,
    val nameAlternative: String?,
    val poster: String,
    val banner: String?,
    val overview: String?,
    val aired: LocalDate?,
    val type: AnimeType,
    val status: AnimeStatus,
    val genres: String,
    val rating: Double,
    val voteAverage: Double,
    val season: String?,
    val broadcast: String?,
    val prequel: String?,
    val sequel: String?,
    val related: String?,
    val views: Long,
    val updatedAt: Long,
    val languages: String,
    val isFavorite: Boolean = false,
    val isWatchLater: Boolean = false,
    val lastViewedAt: Long = 0
)