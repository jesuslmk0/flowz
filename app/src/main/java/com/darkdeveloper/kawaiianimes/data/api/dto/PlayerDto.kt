package com.darkdeveloper.kawaiianimes.data.api.dto

import kotlinx.serialization.Serializable

@Serializable
data class PlayerDto(
    val id: String,
    val embedUrl: String,
    val referer: String,
    val episodeNumber: Int,
    val videoUrl: String?,
    val language: String,
    val servers: List<ServerDto>
) {
    fun toDomain(): Player = Player(
        id = id,
        embedUrl = embedUrl,
        referer = referer,
        episodeNumber = episodeNumber,
        videoUrl = videoUrl,
        language = language,
        servers = servers.map { it.toDomain() }
    )
}