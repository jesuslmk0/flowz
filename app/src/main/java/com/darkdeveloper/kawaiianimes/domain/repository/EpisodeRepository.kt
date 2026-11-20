package com.darkdeveloper.kawaiianimes.domain.repository

import com.darkdeveloper.kawaiianimes.domain.model.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    suspend fun getEpisodesForAnime(animeId: String, language: String?): Result<List<Episode>>
    suspend fun getEpisode(episodeId: String): Result<Episode>
    suspend fun getNextEpisode(animeId: String, currentNumber: Int): Result<Episode?>
    suspend fun getPreviousEpisode(animeId: String, currentNumber: Int): Result<Episode?>
    suspend fun getLanguagesForEpisode(episodeId: String): Result<List<String>>
    fun observeEpisodesForAnime(animeId: String): Flow<List<Episode>>
}