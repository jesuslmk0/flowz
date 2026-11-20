package com.darkdeveloper.kawaiianimes.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_watching", primaryKeys = ["userId", "animeId"])
data class UserWatchingEntity(
    val userId: String,
    val animeId: String,
    val currentEpisode: Int,
    val progress: Float = 0f,
    val updatedAt: Long = System.currentTimeMillis()
)