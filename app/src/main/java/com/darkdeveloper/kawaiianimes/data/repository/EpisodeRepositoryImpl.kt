package com.darkdeveloper.kawaiianimes.data.repository

import com.darkdeveloper.kawaiianimes.data.api.KawaiiApiService
import com.darkdeveloper.kawaiianimes.data.api.dto.EpisodeDto
import com.darkdeveloper.kawaiianimes.data.db.KawaiiDatabase
import com.darkdeveloper.kawaiianimes.data.db.entity.EpisodeEntity
import com.darkdeveloper.kawaiianimes.domain.model.Episode
import com.darkdeveloper.kawaiianimes.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val apiService: KawaiiApiService,
    private val database: KawaiiDatabase
) : EpisodeRepository {

    override suspend fun getEpisodesForAnime(animeId: String, language: String?): Result<List<Episode>> = try {
        val response = apiService.getEpisodesForAnime(animeId, language)
        val entities = response.data.map { it.toEntity(animeId) }
        database.episodeDao().insertAll(entities)
        Result.success(entities.map { it.toDomain() })
    } catch (e: Exception) {
        val cached = database.episodeDao().getByAnimeId(animeId)
        if (cached.isNotEmpty()) {
            Result.success(cached.map { it.toDomain() })
        } else {
            Result.failure(e)
        }
    }

    override suspend fun getEpisode(episodeId: String): Result<Episode> = try {
        val remote = apiService.getEpisode(episodeId)
        val entity = remote.toEntity(remote.animeId)
        database.episodeDao().insert(entity)
        Result.success(entity.toDomain())
    } catch (e: Exception) {
        database.episodeDao().getById(episodeId)?.toDomain()?.let { Result.success(it) }
            ?: Result.failure(e)
    }

    override suspend fun getNextEpisode(animeId: String, currentNumber: Int): Result<Episode?> = try {
        val entity = database.episodeDao().getNext(animeId, currentNumber)
        Result.success(entity?.toDomain())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getPreviousEpisode(animeId: String, currentNumber: Int): Result<Episode?> = try {
        val entity = database.episodeDao().getPrevious(animeId, currentNumber)
        Result.success(entity?.toDomain())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getLanguagesForEpisode(episodeId: String): Result<List<String>> = try {
        val episode = database.episodeDao().getById(episodeId)
        val languages = Json.decodeFromString<List<String>>(episode?.languages ?: "[]")
        Result.success(languages)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override fun observeEpisodesForAnime(animeId: String): Flow<List<Episode>> =
        database.episodeDao().observeByAnimeId(animeId).map { it.map { it.toDomain() } }
}

private fun EpisodeDto.toEntity(animeId: String): EpisodeEntity = EpisodeEntity(
    id = id,
    animeId = animeId,
    number = number,
    title = title,
    overview = overview,
    aired = aired,
    duration = duration,
    thumbnail = thumbnail,
    filler = filler,
    languages = Json.encodeToString(languages.map { it.code }),
    servers = Json.encodeToString(servers ?: emptyList())
)

private fun EpisodeEntity.toDomain(): Episode = Episode(
    id = id,
    animeId = animeId,
    number = number,
    title = title,
    overview = overview,
    aired = aired,
    duration = duration,
    thumbnail = thumbnail,
    filler = filler,
    languages = Json.decodeFromString<List<String>>(languages).map { com.darkdeveloper.kawaiianimes.domain.model.Language(it, it, it) },
    servers = Json.decodeFromString<List<com.darkdeveloper.kawaiianimes.domain.model.Server>>(servers)
)