package com.darkdeveloper.kawaiianimes.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "viewed_episode", primaryKeys = ["userId", "episodeId"])
data class ViewedEpisodeEntity(
    val userId: String,
    val episodeId: String,
    val viewedAt: Long = System.currentTimeMillis(),
    val watchDuration: Long = 0
)