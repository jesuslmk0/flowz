package com.darkdeveloper.kawaiianimes.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "episode",
    foreignKeys = [
        ForeignKey(
            entity = AnimeEntity::class,
            parentColumns = ["id"],
            childColumns = ["animeId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["animeId"]),
        Index(value = ["number"]),
        Index(value = ["languages"])
    ]
)
data class EpisodeEntity(
    @PrimaryKey val id: String,
    val animeId: String,
    val number: Int,
    val title: String?,
    val overview: String?,
    val aired: LocalDateTime?,
    val duration: Int?,
    val thumbnail: String?,
    val filler: Boolean,
    val languages: String,
    val servers: String,
    val isDownloaded: Boolean = false,
    val downloadProgress: Float = 0f,
    val localPath: String?
)

