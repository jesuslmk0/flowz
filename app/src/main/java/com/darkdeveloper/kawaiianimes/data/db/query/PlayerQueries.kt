package com.darkdeveloper.kawaiianimes.data.db.query

import androidx.room.Dao
import androidx.room.Query
import com.darkdeveloper.kawaiianimes.data.db.entity.PlayerEntity
import com.darkdeveloper.kawaiianimes.data.db.entity.ServerEntity
import kotlinx.coroutines.flow.Flow
import java.util.List

@Dao
interface PlayerQueries {

    @Query("SELECT * FROM player WHERE episodeId = :episodeId")
    suspend fun getByEpisodeId(episodeId: String): List<PlayerEntity>

    @Query("SELECT * FROM player WHERE id = :id")
    suspend fun getById(id: String): PlayerEntity?

    @Query("SELECT * FROM server WHERE episodeId = :episodeId")
    suspend fun getServersByEpisodeId(episodeId: String): List<ServerEntity>

    @Query("SELECT * FROM server WHERE id = :id")
    suspend fun getServerById(id: String): ServerEntity?

    @Query("SELECT DISTINCT language FROM player WHERE episodeId = :episodeId")
    suspend fun getLanguagesForEpisode(episodeId: String): List<String>

    @Query("SELECT DISTINCT p.language FROM player p INNER JOIN episode e ON e.id = p.episodeId WHERE e.animeId = :animeId")
    suspend fun getLanguagesForAnime(animeId: String): List<String>
}