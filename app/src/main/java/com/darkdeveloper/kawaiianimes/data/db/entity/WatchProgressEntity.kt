package com.darkdeveloper.kawaiianimes.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watch_progress", primaryKeys = ["userId", "animeId"])
data class WatchProgressEntity(
    val userId: String,
    val animeId: String,
    val episodeId: String,
    val episodeNumber: Int,
    val progress: Float,
    val timestamp: Long
)