package com.darkdeveloper.kawaiianimes.data.repository

import com.darkdeveloper.kawaiianimes.data.api.KawaiiApiService
import com.darkdeveloper.kawaiianimes.data.api.dto.AuthResponseDto
import com.darkdeveloper.kawaiianimes.data.api.dto.UserDto
import com.darkdeveloper.kawaiianimes.data.security.SecureTokenStore
import com.darkdeveloper.kawaiianimes.domain.model.User
import com.darkdeveloper.kawaiianimes.domain.repository.AuthRepository
import kotlinx.serialization.json.Json
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: KawaiiApiService,
    private val tokenStore: SecureTokenStore
) : AuthRepository {

    override suspend fun register(email: String, password: String, username: String): Result<User> = try {
        val request = com.darkdeveloper.kawaiianimes.data.api.RegisterRequest(email, password, username)
        val response = apiService.register(request)
        tokenStore.saveToken(response.token)
        tokenStore.saveUser(response.user.toDomain())
        Result.success(response.user.toDomain())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun login(email: String, password: String): Result<User> = try {
        val request = com.darkdeveloper.kawaiianimes.data.api.LoginRequest(email, password)
        val response = apiService.login(request)
        tokenStore.saveToken(response.token)
        tokenStore.saveUser(response.user.toDomain())
        Result.success(response.user.toDomain())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun logout(): Result<Unit> = try {
        apiService.logout()
        tokenStore.clear()
        Result.success(Unit)
    } catch (e: Exception) {
        tokenStore.clear()
        Result.failure(e)
    }

    override suspend fun getCurrentUser(): Result<User> = try {
        tokenStore.getUser()?.let { Result.success(it) }
            ?: apiService.getUser().toDomain().also { tokenStore.saveUser(it) }.let { Result.success(it) }
    } catch (e: Exception) {
        tokenStore.getUser()?.let { Result.success(it) } ?: Result.failure(e)
    }

    override suspend fun updateUser(username: String?, avatar: String?, theme: String?): Result<User> = try {
        val request = com.darkdeveloper.kawaiianimes.data.api.UpdateUserRequest(username, avatar, theme)
        val user = apiService.updateUser(request)
        tokenStore.saveUser(user.toDomain())
        Result.success(user.toDomain())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun requestPasswordCode(email: String): Result<Unit> = try {
        apiService.requestPasswordCode(com.darkdeveloper.kawaiianimes.data.api.PasswordCodeRequest(email))
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun restorePassword(email: String, code: String, password: String): Result<Unit> = try {
        apiService.restorePassword(com.darkdeveloper.kawaiianimes.data.api.PasswordRestoreRequest(email, code, password))
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getFavorites(): Result<List<String>> = try {
        Result.success(apiService.getFavorites())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun addFavorite(animeId: String): Result<Unit> = try {
        apiService.addFavorite(com.darkdeveloper.kawaiianimes.data.api.FavoriteRequest(animeId))
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun removeFavorite(animeId: String): Result<Unit> = try {
        apiService.removeFavorite(com.darkdeveloper.kawaiianimes.data.api.FavoriteRequest(animeId))
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getWatchLater(): Result<List<String>> = try {
        Result.success(apiService.getWatchLater())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun addWatchLater(animeId: String): Result<Unit> = try {
        apiService.addWatchLater(com.darkdeveloper.kawaiianimes.data.api.WatchLaterRequest(animeId))
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun removeWatchLater(animeId: String): Result<Unit> = try {
        apiService.removeWatchLater(com.darkdeveloper.kawaiianimes.data.api.WatchLaterRequest(animeId))
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getWatching(): Result<List<String>> = try {
        Result.success(apiService.getWatching())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun addWatching(animeId: String, episode: Int): Result<Unit> = try {
        apiService.addWatching(com.darkdeveloper.kawaiianimes.data.api.WatchingRequest(animeId, episode))
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun removeWatching(animeId: String): Result<Unit> = try {
        apiService.removeWatching(com.darkdeveloper.kawaiianimes.data.api.WatchingRequest(animeId, 0))
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getAllLists(): Result<List<com.darkdeveloper.kawaiianimes.domain.model.UserList>> = try {
        Result.success(apiService.getAllLists().map { it.toDomain() })
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun syncLists(favorites: List<String>, watchLater: List<String>, watching: Map<String, Int>, views: List<String>): Result<com.darkdeveloper.kawaiianimes.domain.model.ListDelta> = try {
        val request = com.darkdeveloper.kawaiianimes.data.api.ListSyncRequest(favorites, watchLater, watching, views)
        val response = apiService.syncLists(request)
        Result.success(response.toDomain())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun restoreFromApp(backupData: String): Result<com.darkdeveloper.kawaiianimes.domain.model.ListDelta> = try {
        val request = com.darkdeveloper.kawaiianimes.data.api.RestoreFromAppRequest(backupData)
        val response = apiService.restoreFromApp(request)
        Result.success(response.toDomain())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun updateProgress(animeId: String, episode: Int, progress: Float, timestamp: Long): Result<Unit> = try {
        val request = com.darkdeveloper.kawaiianimes.data.api.ProgressRequest(animeId, episode, progress, timestamp)
        apiService.updateProgress(request)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun deleteAnimeProgress(animeId: String): Result<Unit> = try {
        apiService.deleteAnimeProgress(com.darkdeveloper.kawaiianimes.data.api.DeleteProgressRequest(animeId))
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getPointsStatus(): Result<com.darkdeveloper.kawaiianimes.domain.model.PointsStatus> = try {
        Result.success(apiService.getPointsStatus().toDomain())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun earnPoints(amount: Int, source: String): Result<Unit> = try {
        apiService.earnPoints(com.darkdeveloper.kawaiianimes.data.api.EarnPointsRequest(amount, source))
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun spendPoints(amount: Int, target: String): Result<Unit> = try {
        apiService.spendPoints(com.darkdeveloper.kawaiianimes.data.api.SpendPointsRequest(amount, target))
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getPremiumEntitlement(): Result<com.darkdeveloper.kawaiianimes.domain.model.PremiumEntitlement> = try {
        Result.success(apiService.getPremiumEntitlement().toDomain())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun renewPremium(receipt: String, productId: String): Result<Unit> = try {
        apiService.renewPremium(com.darkdeveloper.kawaiianimes.data.api.RenewPremiumRequest(receipt, productId))
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getViews(): Result<List<String>> = try {
        Result.success(apiService.getViews())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun addView(animeId: String): Result<Unit> = try {
        apiService.addView(com.darkdeveloper.kawaiianimes.data.api.ViewRequest(animeId))
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun removeView(animeId: String): Result<Unit> = try {
        apiService.removeView(com.darkdeveloper.kawaiianimes.data.api.ViewRequest(animeId))
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun sync(): Result<com.darkdeveloper.kawaiianimes.domain.model.SyncResult> = try {
        Result.success(apiService.sync().toDomain())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun bulkSync(data: String): Result<com.darkdeveloper.kawaiianimes.domain.model.SyncResult> = try {
        Result.success(apiService.bulkSync(com.darkdeveloper.kawaiianimes.data.api.BulkSyncRequest(data)).toDomain())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun createMpPreference(items: List<String>): Result<com.darkdeveloper.kawaiianimes.domain.model.MpPreference> = try {
        Result.success(apiService.createMpPreference(com.darkdeveloper.kawaiianimes.data.api.MpPreferenceRequest(items)).toDomain())
    } catch (e: Exception) {
        Result.failure(e)
    }
}

private fun UserDto.toDomain(): User = User(
    id = id,
    email = email,
    username = username,
    avatar = avatar,
    isPremium = isPremium,
    premiumExpiresAt = premiumExpiresAt,
    theme = theme,
    language = language
)

private fun AuthResponseDto.toDomain(): User = user.toDomain()

private fun com.darkdeveloper.kawaiianimes.data.api.dto.PointsResponseDto.toDomain(): com.darkdeveloper.kawaiianimes.domain.model.PointsStatus = com.darkdeveloper.kawaiianimes.domain.model.PointsStatus(
    balance = balance,
    history = history.map { it.toDomain() }
)

private fun com.darkdeveloper.kawaiianimes.data.api.dto.PointTransactionDto.toDomain(): com.darkdeveloper.kawaiianimes.domain.model.PointTransaction = com.darkdeveloper.kawaiianimes.domain.model.PointTransaction(
    id = id,
    amount = amount,
    type = com.darkdeveloper.kawaiianimes.domain.model.PointTransactionType.valueOf(type),
    source = source,
    createdAt = createdAt
)

private fun com.darkdeveloper.kawaiianimes.data.api.dto.PremiumEntitlementDto.toDomain(): com.darkdeveloper.kawaiianimes.domain.model.PremiumEntitlement = com.darkdeveloper.kawaiianimes.domain.model.PremiumEntitlement(
    isActive = isActive,
    expiresAt = expiresAt,
    productId = productId
)

private fun com.darkdeveloper.kawaiianimes.data.api.dto.ListItemDto.toDomain(): com.darkdeveloper.kawaiianimes.domain.model.UserList = com.darkdeveloper.kawaiianimes.domain.model.UserList(
    id = id,
    name = name,
    description = description,
    animeCount = animeCount,
    isPublic = isPublic
)

private fun com.darkdeveloper.kawaiianimes.data.api.dto.ListDeltaResponse.toDomain(): com.darkdeveloper.kawaiianimes.domain.model.ListDelta = com.darkdeveloper.kawaiianimes.domain.model.ListDelta(
    added = added,
    removed = removed,
    updated = updated
)

private fun com.darkdeveloper.kawaiianimes.data.api.dto.SyncResponse.toDomain(): com.darkdeveloper.kawaiianimes.domain.model.SyncResult = com.darkdeveloper.kawaiianimes.domain.model.SyncResult(
    success = success,
    message = message
)

private fun com.darkdeveloper.kawaiianimes.data.api.dto.MpPreferenceResponse.toDomain(): com.darkdeveloper.kawaiianimes.domain.model.MpPreference = com.darkdeveloper.kawaiianimes.domain.model.MpPreference(
    id = id,
    initPoint = initPoint,
    sandboxInitPoint = sandboxInitPoint
)