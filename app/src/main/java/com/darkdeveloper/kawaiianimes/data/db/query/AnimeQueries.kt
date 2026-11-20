package com.darkdeveloper.kawaiianimes.data.db.query

import androidx.room.Dao
import androidx.room.Query
import com.darkdeveloper.kawaiianimes.data.db.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow
import java.util.List

@Dao
interface AnimeQueries {

    @Query("""
        SELECT a.*, COUNT(e.id) as episode_count
        FROM anime a
        LEFT JOIN episode e ON e.animeId = a.id
        GROUP BY a.id
        HAVING episode_count > 0
        ORDER BY a.views DESC
        LIMIT :limit
    """)
    suspend fun getAnimesWithEpisodes(limit: Int): List<AnimeEntity>

    @Query("""
        SELECT DISTINCT a.*
        FROM anime a
        INNER JOIN episode e ON e.animeId = a.id
        WHERE e.languages LIKE '%' || :language || '%'
        ORDER BY a.updatedAt DESC
        LIMIT :limit OFFSET :offset
    """)
    suspend fun getAnimesByLanguage(language: String, limit: Int, offset: Int): List<AnimeEntity>

    @Query("SELECT * FROM anime WHERE id IN (:ids)")
    suspend fun getByIds(ids: List<String>): List<AnimeEntity>
}