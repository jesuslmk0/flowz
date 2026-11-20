package com.darkdeveloper.kawaiianimes.data.db.query

import androidx.room.Dao
import androidx.room.Query
import com.darkdeveloper.kawaiianimes.data.db.entity.*
import kotlinx.coroutines.flow.Flow
import java.util.List

@Dao
interface UserDataQueries {

    @Query("SELECT a.* FROM anime a INNER JOIN user_favorite uf ON uf.animeId = a.id WHERE uf.userId = :userId ORDER BY uf.addedAt DESC")
    suspend fun getFavoriteAnimes(userId: String): List<AnimeEntity>

    @Query("SELECT a.* FROM anime a INNER JOIN user_watch_later uwl ON uwl.animeId = a.id WHERE uwl.userId = :userId ORDER BY uwl.addedAt DESC")
    suspend fun getWatchLaterAnimes(userId: String): List<AnimeEntity>

    @Query("SELECT a.*, uw.currentEpisode, uw.progress FROM anime a INNER JOIN user_watching uw ON uw.animeId = a.id WHERE uw.userId = :userId ORDER BY uw.updatedAt DESC")
    suspend fun getWatchingAnimes(userId: String): List<AnimeEntity>

    @Query("SELECT e.*, a.name as animeName, a.poster as animePoster FROM episode e INNER JOIN anime a ON a.id = e.animeId INNER JOIN viewed_episode ve ON ve.episodeId = e.id WHERE ve.userId = :userId ORDER BY ve.viewedAt DESC LIMIT :limit")
    suspend fun getRecentViewedEpisodes(userId: String, limit: Int): List<EpisodeEntity>

    @Query("SELECT * FROM downloaded_episode WHERE ownerUserId = :userId ORDER BY createdAt DESC")
    suspend fun getUserDownloads(userId: String): List<DownloadedEpisodeEntity>
}