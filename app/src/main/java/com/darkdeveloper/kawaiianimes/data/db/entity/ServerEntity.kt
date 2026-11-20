package com.darkdeveloper.kawaiianimes.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "server")
data class ServerEntity(
    @PrimaryKey val id: String,
    val episodeId: String,
    val name: String,
    val type: Int,
    val embedUrl: String,
    val quality: Int?,
    val language: String,
    val status: Int,
    val isPremium: Boolean = false
)