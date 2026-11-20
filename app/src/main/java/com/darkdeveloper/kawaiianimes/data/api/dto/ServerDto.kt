package com.darkdeveloper.kawaiianimes.data.api.dto

import kotlinx.serialization.Serializable

@Serializable
data class ServerDto(
    val id: String,
    val name: String,
    val type: Int,
    val embedUrl: String,
    val quality: Int?,
    val language: String?,
    val isPremium: Boolean
) {
    fun toDomain(): Server = Server(
        id = id,
        name = name,
        type = ServerType.fromValue(type),
        embedUrl = embedUrl,
        quality = quality,
        language = language ?: "sub",
        isPremium = isPremium
    )
}

enum class ServerType(val value: Int) {
    STREAMWISH(1),
    FILEMOON(2),
    MP4UPLOAD(3),
    VOE(4),
    UPSTREAM(5),
    UNKNOWN(999);

    companion object {
        fun fromValue(value: Int): ServerType = values().firstOrNull { it.value == value } ?: UNKNOWN
    }
}