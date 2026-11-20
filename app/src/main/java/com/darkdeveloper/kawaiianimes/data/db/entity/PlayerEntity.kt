package com.darkdeveloper.kawaiianimes.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player")
data class PlayerEntity(
    @PrimaryKey val id: String,
    val episodeId: String,
    val embedUrl: String,
    val referer: String,
    val language: String,
    val videoUrl: String?,
    val servers: String
)