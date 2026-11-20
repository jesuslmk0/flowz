package com.darkdeveloper.kawaiianimes.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_watch_later", primaryKeys = ["userId", "animeId"])
data class UserWatchLaterEntity(
    val userId: String,
    val animeId: String,
    val addedAt: Long = System.currentTimeMillis()
)