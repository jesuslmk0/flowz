package com.darkdeveloper.kawaiianimes.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloaded_episode")
data class DownloadedEpisodeEntity(
    @PrimaryKey val id: String,
    val animeId: String,
    val animeName: String,
    val animePoster: String,
    val episodeNumber: Int,
    val episodeId: String,
    val language: String,
    val serverName: String,
    val playerId: String,
    val downloadStatus: Int,
    val downloadProgress: Float,
    val totalBytes: Long,
    val downloadedBytes: Long,
    val localPath: String?,
    val streamUrl: String,
    val createdAt: Long,
    val completedAt: Long?,
    val lastError: String?,
    val ownerUserId: String
)