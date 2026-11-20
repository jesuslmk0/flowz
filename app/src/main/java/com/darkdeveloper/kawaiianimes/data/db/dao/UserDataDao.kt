package com.darkdeveloper.kawaiianimes.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.darkdeveloper.kawaiianimes.data.db.entity.*
import kotlinx.coroutines.flow.Flow
import java.util.List

@Dao
interface UserDataDao {

    // Favorites
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: UserFavoriteEntity): Long

    @Query("DELETE FROM user_favorite WHERE userId = :userId AND animeId = :animeId")
    suspend fun removeFavorite(userId: String, animeId: String): Int

    @Query("SELECT * FROM user_favorite WHERE userId = :userId")
    suspend fun getFavorites(userId: String): List<UserFavoriteEntity>

    @Query("SELECT * FROM user_favorite WHERE userId = :userId")
    fun observeFavorites(userId: String): Flow<List<UserFavoriteEntity>>

    // Watch Later
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchLater(watchLater: UserWatchLaterEntity): Long

    @Query("DELETE FROM user_watch_later WHERE userId = :userId AND animeId = :animeId")
    suspend fun removeWatchLater(userId: String, animeId: String): Int

    @Query("SELECT * FROM user_watch_later WHERE userId = :userId")
    suspend fun getWatchLater(userId: String): List<UserWatchLaterEntity>

    // Watching
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatching(watching: UserWatchingEntity): Long

    @Query("DELETE FROM user_watching WHERE userId = :userId AND animeId = :animeId")
    suspend fun removeWatching(userId: String, animeId: String): Int

    @Query("SELECT * FROM user_watching WHERE userId = :userId")
    suspend fun getWatching(userId: String): List<UserWatchingEntity>

    @Query("SELECT * FROM user_watching WHERE userId = :userId AND animeId = :animeId")
    suspend fun getWatchingForAnime(userId: String, animeId: String): UserWatchingEntity?

    // Viewed Episodes
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertViewedEpisode(viewed: ViewedEpisodeEntity): Long

    @Query("SELECT * FROM viewed_episode WHERE userId = :userId ORDER BY viewedAt DESC")
    suspend fun getViewedEpisodes(userId: String): List<ViewedEpisodeEntity>

    // Watch Progress
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchProgress(progress: WatchProgressEntity): Long

    @Query("SELECT * FROM watch_progress WHERE userId = :userId AND animeId = :animeId")
    suspend fun getWatchProgress(userId: String, animeId: String): WatchProgressEntity?

    @Query("SELECT * FROM watch_progress WHERE userId = :userId ORDER BY timestamp DESC")
    suspend fun getAllWatchProgress(userId: String): List<WatchProgressEntity>

    // Downloaded Episodes
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownloadedEpisode(download: DownloadedEpisodeEntity): Long

    @Query("UPDATE downloaded_episode SET downloadStatus = :status, downloadProgress = :progress, downloadedBytes = :bytes, lastError = :error WHERE id = :id")
    suspend fun updateDownloadStatus(id: String, status: Int, progress: Float, bytes: Long, error: String?): Int

    @Query("SELECT * FROM downloaded_episode WHERE ownerUserId = :userId ORDER BY createdAt DESC")
    suspend fun getDownloadedEpisodes(userId: String): List<DownloadedEpisodeEntity>

    @Query("SELECT * FROM downloaded_episode WHERE ownerUserId = :userId AND downloadStatus != 2")
    suspend fun getActiveDownloads(userId: String): List<DownloadedEpisodeEntity>

    // Searched Anime
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchedAnime(searched: SearchedAnimeEntity): Long

    @Query("SELECT * FROM searched_anime ORDER BY searchedAt DESC LIMIT :limit")
    suspend fun getRecentSearches(limit: Int): List<SearchedAnimeEntity>
}