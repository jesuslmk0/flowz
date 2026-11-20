package com.darkdeveloper.kawaiianimes.data.db.query

import androidx.room.Dao
import androidx.room.Query
import com.darkdeveloper.kawaiianimes.data.db.entity.EpisodeEntity
import kotlinx.coroutines.flow.Flow
import java.util.List

@Dao
interface EpisodeQueries {

    @Query("SELECT * FROM episode WHERE animeId = :animeId ORDER BY number ASC")
    suspend fun getByAnimeId(animeId: String): List<EpisodeEntity>

    @Query("SELECT * FROM episode WHERE animeId = :animeId ORDER BY number ASC")
    fun observeByAnimeId(animeId: String): Flow<List<EpisodeEntity>>

    @Query("SELECT * FROM episode WHERE id = :id")
    suspend fun getById(id: String): EpisodeEntity?

    @Query("SELECT * FROM episode WHERE animeId = :animeId AND number = :number")
    suspend fun getByNumber(animeId: String, number: Int): EpisodeEntity?

    @Query("SELECT * FROM episode WHERE animeId = :animeId AND number > :currentNumber ORDER BY number ASC LIMIT 1")
    suspend fun getNext(animeId: String, currentNumber: Int): EpisodeEntity?

    @Query("SELECT * FROM episode WHERE animeId = :animeId AND number < :currentNumber ORDER BY number DESC LIMIT 1")
    suspend fun getPrevious(animeId: String, currentNumber: Int): EpisodeEntity?

    @Query("SELECT * FROM episode WHERE languages LIKE '%' || :language || '%' ORDER BY aired DESC LIMIT :limit")
    suspend fun getRecentByLanguage(language: String, limit: Int): List<EpisodeEntity>

    @Query("SELECT COUNT(*) FROM episode WHERE animeId = :animeId")
    suspend fun countByAnimeId(animeId: String): Int
}