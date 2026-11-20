package com.darkdeveloper.kawaiianimes.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.darkdeveloper.kawaiianimes.data.db.entity.ServerEntity
import kotlinx.coroutines.flow.Flow
import java.util.List

@Dao
interface ServerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(servers: List<ServerEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(server: ServerEntity): Long

    @Query("SELECT * FROM server WHERE episodeId = :episodeId")
    suspend fun getByEpisodeId(episodeId: String): List<ServerEntity>

    @Query("SELECT * FROM server WHERE id = :id")
    suspend fun getById(id: String): ServerEntity?
}