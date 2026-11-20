package com.darkdeveloper.kawaiianimes.data.repository

import com.darkdeveloper.kawaiianimes.data.api.KawaiiApiService
import com.darkdeveloper.kawaiianimes.data.api.dto.PlayerDto
import com.darkdeveloper.kawaiianimes.data.api.dto.ServerDto
import com.darkdeveloper.kawaiianimes.data.db.KawaiiDatabase
import com.darkdeveloper.kawaiianimes.data.db.entity.PlayerEntity
import com.darkdeveloper.kawaiianimes.data.db.entity.ServerEntity
import com.darkdeveloper.kawaiianimes.data.extractors.ExtractorRegistry
import com.darkdeveloper.kawaiianimes.data.extractors.ExtractResult
import com.darkdeveloper.kawaiianimes.domain.model.Player
import com.darkdeveloper.kawaiianimes.domain.model.Server
import com.darkdeveloper.kawaiianimes.domain.repository.PlayerRepository
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json
import javax.inject.Inject

class PlayerRepositoryImpl @Inject constructor(
    private val apiService: KawaiiApiService,
    private val database: KawaiiDatabase,
    private val extractorRegistry: ExtractorRegistry
) : PlayerRepository {

    override suspend fun getServers(animeId: String, episode: Int, language: String): Result<List<Server>> = try {
        val dtos = apiService.getServers(animeId, episode, language)
        val servers = dtos.map { it.toDomain() }
        database.runTransaction {
            servers.forEach { server ->
                database.serverDao().insert(ServerEntity(
                    id = server.id,
                    episodeId = "${animeId}_ep$episode",
                    name = server.name,
                    type = server.type.value,
                    embedUrl = server.embedUrl,
                    quality = server.quality,
                    language = server.language,
                    status = 0,
                    isPremium = server.isPremium
                ))
            }
        }
        Result.success(servers)
    } catch (e: Exception) {
        val cached = database.serverDao().getByEpisodeId("${animeId}_ep$episode")
        if (cached.isNotEmpty()) {
            Result.success(cached.map { it.toDomain() })
        } else {
            Result.failure(e)
        }
    }

    override suspend fun getServersForPlayer(playerId: String): Result<List<Server>> = try {
        val cached = database.serverDao().getByPlayerId(playerId)
        if (cached.isNotEmpty()) {
            Result.success(cached.map { it.toDomain() })
        } else {
            Result.failure(IllegalStateException("No servers cached for player $playerId"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getPlayersForEpisode(episodeId: String): Result<List<Player>> = try {
        val players = database.playerDao().getByEpisodeId(episodeId)
        Result.success(players.map { it.toDomain() })
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getPlayersForEpisodeAndLanguage(episodeId: String, language: String): Result<List<Player>> = try {
        val players = database.playerDao().getByEpisodeId(episodeId)
            .filter { it.language == language }
        Result.success(players.map { it.toDomain() })
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getPlayerById(playerId: String): Result<Player> = try {
        database.playerDao().getById(playerId)?.toDomain()?.let { Result.success(it) }
            ?: Result.failure(IllegalStateException("Player not found: $playerId"))
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getAvailableLanguages(animeId: String): Result<List<String>> = try {
        val languages = database.playerQueries().getLanguagesForAnime(animeId)
        Result.success(languages)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getAvailableLanguagesByAnimeNumber(animeNumber: Int): Result<List<String>> = try {
        Result.success(listOf("sub", "es", "lat"))
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun buildPlayerUrl(server: Server, episode: Int, language: String): Result<String> {
        return if (server.isExtracted) {
            Result.success(server.videoUrl!!)
        } else {
            extractorRegistry.extractWithFallback(server).fold(
                onSuccess = { result ->
                    if (result is ExtractResult.Success) {
                        val updatedServer = server.copy(
                            videoUrl = result.playableUrl,
                            referer = result.headers[HttpHeaders.Referer] ?: server.referer,
                            extractedAt = System.currentTimeMillis()
                        )
                        database.serverDao().insert(ServerEntity(
                            id = server.id,
                            episodeId = "",
                            name = server.name,
                            type = server.type.value,
                            embedUrl = server.embedUrl,
                            quality = server.quality,
                            language = server.language,
                            status = 1,
                            isPremium = server.isPremium
                        ))
                        Result.success(result.playableUrl)
                    } else {
                        Result.failure(Exception(result.error))
                    }
                },
                onFailure = { Result.failure(it) }
            )
        }
    }
}

private fun ServerDto.toDomain(): Server = Server(
    id = id,
    name = name,
    type = ServerType.fromValue(type),
    embedUrl = embedUrl,
    quality = quality,
    language = language ?: "sub",
    isPremium = isPremium
)

private fun PlayerEntity.toDomain(): Player = Player(
    id = id,
    embedUrl = embedUrl,
    referer = referer,
    episodeNumber = episodeNumber,
    videoUrl = videoUrl,
    language = language,
    servers = Json.decodeFromString<List<Server>>(servers)
)

private fun ServerEntity.toDomain(): Server = Server(
    id = id,
    name = name,
    type = ServerType.fromValue(type),
    embedUrl = embedUrl,
    quality = quality,
    language = language,
    isPremium = isPremium
)