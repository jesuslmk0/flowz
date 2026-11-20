package com.darkdeveloper.kawaiianimes.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.darkdeveloper.kawaiianimes.data.db.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow
import java.util.List

@Dao
interface AnimeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(animes: List<AnimeEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(anime: AnimeEntity): Long

    @Update
    suspend fun update(anime: AnimeEntity): Int

    @Query("SELECT * FROM anime WHERE id = :id")
    suspend fun getById(id: String): AnimeEntity?

    @Query("SELECT * FROM anime WHERE id = :id")
    fun observeById(id: String): Flow<AnimeEntity?>

    @Query("SELECT * FROM anime ORDER BY updatedAt DESC LIMIT :limit OFFSET :offset")
    suspend fun getAll(limit: Int, offset: Int): List<AnimeEntity>

    @Query("SELECT * FROM anime ORDER BY updatedAt DESC LIMIT :limit OFFSET :offset")
    fun observeAll(limit: Int, offset: Int): Flow<List<AnimeEntity>>

    @Query("SELECT * FROM anime WHERE name LIKE '%' || :query || '%' OR nameAlternative LIKE '%' || :query || '%' ORDER BY views DESC LIMIT :limit")
    suspend fun search(query: String, limit: Int): List<AnimeEntity>

    @Query("SELECT * FROM anime WHERE :genre IN (SELECT value FROM json_each(genres)) ORDER BY views DESC LIMIT :limit OFFSET :offset")
    suspend fun getByGenre(genre: String, limit: Int, offset: Int): List<AnimeEntity>

    @Query("SELECT * FROM anime WHERE status = :status ORDER BY updatedAt DESC LIMIT :limit OFFSET :offset")
    suspend fun getByStatus(status: String, limit: Int, offset: Int): List<AnimeEntity>

    @Query("SELECT * FROM anime WHERE type = :type ORDER BY updatedAt DESC LIMIT :limit OFFSET :offset")
    suspend fun getByType(type: String, limit: Int, offset: Int): List<AnimeEntity>

    @Query("SELECT * FROM anime WHERE :dub IN (SELECT value FROM json_each(languages)) ORDER BY updatedAt DESC LIMIT :limit OFFSET :offset")
    suspend fun getByDub(dub: String, limit: Int, offset: Int): List<AnimeEntity>

    @Query("SELECT * FROM anime ORDER BY views DESC LIMIT :limit OFFSET :offset")
    suspend fun getMostViewed(limit: Int, offset: Int): List<AnimeEntity>

    @Query("SELECT * FROM anime WHERE aired >= date('now') AND aired <= date('now', '+7 days') ORDER BY aired ASC")
    suspend fun getThisWeekCalendar(): List<AnimeEntity>

    @Query("SELECT * FROM anime WHERE isFavorite = 1 ORDER BY lastViewedAt DESC")
    suspend fun getFavorites(): List<AnimeEntity>

    @Query("SELECT * FROM anime WHERE isWatchLater = 1 ORDER BY lastViewedAt DESC")
    suspend fun getWatchLater(): List<AnimeEntity>

    @Query("UPDATE anime SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun setFavorite(id: String, isFavorite: Boolean): Int

    @Query("UPDATE anime SET isWatchLater = :isWatchLater WHERE id = :id")
    suspend fun setWatchLater(id: String, isWatchLater: Boolean): Int

    @Query("UPDATE anime SET lastViewedAt = :timestamp WHERE id = :id")
    suspend fun updateLastViewed(id: String, timestamp: Long): Int

    @Query("DELETE FROM anime WHERE updatedAt < :threshold")
    suspend fun cleanupOld(threshold: Long): Int
}