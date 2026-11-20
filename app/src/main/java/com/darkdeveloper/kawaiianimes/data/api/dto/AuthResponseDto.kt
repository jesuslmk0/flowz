package com.darkdeveloper.kawaiianimes.data.api.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(
    val token: String,
    val user: UserDto,
    val expiresIn: Long
)

@Serializable
data class UserDto(
    val id: String,
    val email: String,
    val username: String,
    val avatar: String?,
    val isPremium: Boolean,
    val premiumExpiresAt: Long?,
    val theme: String?,
    val language: String?
)

@Serializable
data class PointsResponseDto(
    val balance: Int,
    val history: List<PointTransactionDto>
)

@Serializable
data class PointTransactionDto(
    val id: String,
    val amount: Int,
    val type: String,
    val source: String,
    val createdAt: Long
)

@Serializable
data class PremiumEntitlementDto(
    val isActive: Boolean,
    val expiresAt: Long?,
    val productId: String?
)

@Serializable
data class MpPreferenceResponse(
    val id: String,
    val initPoint: String,
    val sandboxInitPoint: String
)

@Serializable
data class ListItemDto(
    val id: String,
    val name: String,
    val description: String?,
    val animeCount: Int,
    val isPublic: Boolean
)

@Serializable
data class ListDeltaDto(
    val animeId: String,
    val action: String,
    val timestamp: Long
)