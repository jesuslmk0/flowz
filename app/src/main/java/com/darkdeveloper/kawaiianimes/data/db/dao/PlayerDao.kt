package com.darkdeveloper.kawaiianimes.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.darkdeveloper.kawaiianimes.data.db.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow
import java.util.List

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(players: List<PlayerEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(player: PlayerEntity): Long

    @Query("SELECT * FROM player WHERE episodeId = :episodeId")
    suspend fun getByEpisodeId(episodeId: String): List<PlayerEntity>

    @Query("SELECT * FROM player WHERE id = :id")
    suspend fun getById(id: String): PlayerEntity?

    @Query("SELECT DISTINCT language FROM player WHERE episodeId = :episodeId")
    suspend fun getLanguagesForEpisode(episodeId: String): List<String>

    @Query("SELECT DISTINCT p.language FROM player p INNER JOIN episode e ON e.id = p.episodeId WHERE e.animeId = :animeId")
    suspend fun getLanguagesForAnime(animeId: String): List<String>
}