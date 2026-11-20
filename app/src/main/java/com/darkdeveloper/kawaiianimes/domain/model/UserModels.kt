package com.darkdeveloper.kawaiianimes.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
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
data class PointTransaction(
    val id: String,
    val amount: Int,
    val type: PointTransactionType,
    val source: String,
    val createdAt: Long
)

enum class PointTransactionType { EARN, SPEND }

@Serializable
data class PointsStatus(
    val balance: Int,
    val history: List<PointTransaction>
)

@Serializable
data class PremiumEntitlement(
    val isActive: Boolean,
    val expiresAt: Long?,
    val productId: String?
)

@Serializable
data class UserList(
    val id: String,
    val name: String,
    val description: String?,
    val animeCount: Int,
    val isPublic: Boolean
)

@Serializable
data class ListDelta(
    val added: List<String>,
    val removed: List<String>,
    val updated: Map<String, Any>
)

@Serializable
data class SyncResult(
    val success: Boolean,
    val message: String?
)

@Serializable
data class MpPreference(
    val id: String,
    val initPoint: String,
    val sandboxInitPoint: String
)