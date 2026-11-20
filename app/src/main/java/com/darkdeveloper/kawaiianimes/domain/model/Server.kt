package com.darkdeveloper.kawaiianimes.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Server(
    val id: String,
    val name: String,
    val type: ServerType,
    val embedUrl: String,
    val quality: Int?,
    val language: String,
    val isPremium: Boolean,
    val referer: String = "",
    val videoUrl: String? = null,
    val extractedAt: Long = 0
) {
    val displayName: String = "$name ${quality?.let { "${it}p" } ?: ""}".trim()
    val isExtracted: Boolean = videoUrl != null && videoUrl!!.isNotBlank()
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