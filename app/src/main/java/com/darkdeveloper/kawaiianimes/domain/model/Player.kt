package com.darkdeveloper.kawaiianimes.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val id: String,
    val embedUrl: String,
    val referer: String,
    val episodeNumber: Int,
    val videoUrl: String?,
    val language: String,
    val servers: List<Server>
)