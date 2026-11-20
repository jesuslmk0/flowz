package com.darkdeveloper.kawaiianimes.domain.repository

import com.darkdeveloper.kawaiianimes.domain.model.ListDelta
import com.darkdeveloper.kawaiianimes.domain.model.MpPreference
import com.darkdeveloper.kawaiianimes.domain.model.PointsStatus
import com.darkdeveloper.kawaiianimes.domain.model.PremiumEntitlement
import com.darkdeveloper.kawaiianimes.domain.model.SyncResult
import com.darkdeveloper.kawaiianimes.domain.model.User
import com.darkdeveloper.kawaiianimes.domain.model.UserList

interface AuthRepository {
    suspend fun register(email: String, password: String, username: String): Result<User>
    suspend fun login(email: String, password: String): Result<User>
    suspend fun logout(): Result<Unit>
    suspend fun getCurrentUser(): Result<User>
    suspend fun updateUser(username: String?, avatar: String?, theme: String?): Result<User>
    suspend fun requestPasswordCode(email: String): Result<Unit>
    suspend fun restorePassword(email: String, code: String, password: String): Result<Unit>

    suspend fun getFavorites(): Result<List<String>>
    suspend fun addFavorite(animeId: String): Result<Unit>
    suspend fun removeFavorite(animeId: String): Result<Unit>

    suspend fun getWatchLater(): Result<List<String>>
    suspend fun addWatchLater(animeId: String): Result<Unit>
    suspend fun removeWatchLater(animeId: String): Result<Unit>

    suspend fun getWatching(): Result<List<String>>
    suspend fun addWatching(animeId: String, episode: Int): Result<Unit>
    suspend fun removeWatching(animeId: String): Result<Unit>

    suspend fun getAllLists(): Result<List<UserList>>
    suspend fun syncLists(favorites: List<String>, watchLater: List<String>, watching: Map<String, Int>, views: List<String>): Result<ListDelta>
    suspend fun restoreFromApp(backupData: String): Result<ListDelta>

    suspend fun updateProgress(animeId: String, episode: Int, progress: Float, timestamp: Long): Result<Unit>
    suspend fun deleteAnimeProgress(animeId: String): Result<Unit>

    suspend fun getPointsStatus(): Result<PointsStatus>
    suspend fun earnPoints(amount: Int, source: String): Result<Unit>
    suspend fun spendPoints(amount: Int, target: String): Result<Unit>

    suspend fun getPremiumEntitlement(): Result<PremiumEntitlement>
    suspend fun renewPremium(receipt: String, productId: String): Result<Unit>

    suspend fun getViews(): Result<List<String>>
    suspend fun addView(animeId: String): Result<Unit>
    suspend fun removeView(animeId: String): Result<Unit>

    suspend fun sync(): Result<SyncResult>
    suspend fun bulkSync(data: String): Result<SyncResult>
    suspend fun createMpPreference(items: List<String>): Result<MpPreference>
}