package com.darkdeveloper.kawaiianimes.domain.repository

import com.darkdeveloper.kawaiianimes.domain.model.Player
import com.darkdeveloper.kawaiianimes.domain.model.Server
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    suspend fun getServers(animeId: String, episode: Int, language: String): Result<List<Server>>
    suspend fun getServersForPlayer(playerId: String): Result<List<Server>>
    suspend fun getPlayersForEpisode(episodeId: String): Result<List<Player>>
    suspend fun getPlayersForEpisodeAndLanguage(episodeId: String, language: String): Result<List<Player>>
    suspend fun getPlayerById(playerId: String): Result<Player>
    suspend fun getAvailableLanguages(animeId: String): Result<List<String>>
    suspend fun getAvailableLanguagesByAnimeNumber(animeNumber: Int): Result<List<String>>
    suspend fun buildPlayerUrl(server: Server, episode: Int, language: String): Result<String>
}