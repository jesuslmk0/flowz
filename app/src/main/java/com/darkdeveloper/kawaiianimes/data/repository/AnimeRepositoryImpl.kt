package com.darkdeveloper.kawaiianimes.data.repository

import com.darkdeveloper.kawaiianimes.data.api.KawaiiApiService
import com.darkdeveloper.kawaiianimes.data.api.dto.AnimeDto
import com.darkdeveloper.kawaiianimes.data.db.KawaiiDatabase
import com.darkdeveloper.kawaiianimes.data.db.entity.AnimeEntity
import com.darkdeveloper.kawaiianimes.domain.model.Anime
import com.darkdeveloper.kawaiianimes.domain.model.Language
import com.darkdeveloper.kawaiianimes.domain.repository.AnimeRepository
import com.darkdeveloper.kawaiianimes.domain.repository.SearchFilters
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val apiService: KawaiiApiService,
    private val database: KawaiiDatabase
) : AnimeRepository {

    override suspend fun getAnimeById(id: String): Result<Anime> = try {
        val remote = apiService.getAnimeById(id)
        val entity = remote.toEntity()
        database.animeDao().insert(entity)
        Result.success(entity.toDomain())
    } catch (e: Exception) {
        database.animeDao().getById(id)?.toDomain()?.let { Result.success(it) }
            ?: Result.failure(e)
    }

    override suspend fun getPopularAnimes(page: Int, limit: Int): Result<List<Anime>> = safeRemoteList {
        apiService.getPopularAnimes(page, limit).data
    }

    override suspend fun getNewAnimes(page: Int, limit: Int): Result<List<Anime>> = safeRemoteList {
        apiService.getNewAnimes(page, limit).data
    }

    override suspend fun getRecentAnimes(page: Int, limit: Int): Result<List<Anime>> = safeRemoteList {
        apiService.getRecentAnimes(page, limit).data
    }

    override suspend fun getRecommendations(page: Int, limit: Int): Result<List<Anime>> = safeRemoteList {
        apiService.getRecommendations(page, limit).data
    }

    override suspend fun getRelatedAnimes(animeId: String, page: Int, limit: Int): Result<List<Anime>> = safeRemoteList {
        apiService.getRelatedAnimes(animeId, page, limit).data
    }

    override suspend fun getAnimesByGenre(genre: String, page: Int, limit: Int): Result<List<Anime>> = safeRemoteList {
        apiService.getAnimesByGenre(genre, page, limit).data
    }

    override suspend fun getAnimesByStatus(status: String, page: Int, limit: Int): Result<List<Anime>> = safeRemoteList {
        apiService.getAnimesByStatus(status, page, limit).data
    }

    override suspend fun getAnimesByType(type: String, page: Int, limit: Int): Result<List<Anime>> = safeRemoteList {
        apiService.getAnimesByType(type, page, limit).data
    }

    override suspend fun getMovies(page: Int, limit: Int): Result<List<Anime>> = safeRemoteList {
        apiService.getMovies(page, limit).data
    }

    override suspend fun getCalendar(season: String?, year: Int?): Result<Map<String, List<Anime>>> = try {
        val response = apiService.getCalendar(season, year)
        Result.success(response.data.mapValues { (k, v) -> k to v.map { it.toDomain() } })
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getWithCastellano(page: Int, limit: Int): Result<List<Anime>> = safeRemoteList {
        apiService.getWithCastellano(page, limit).data
    }

    override suspend fun getWithLatino(page: Int, limit: Int): Result<List<Anime>> = safeRemoteList {
        apiService.getWithLatino(page, limit).data
    }

    override suspend fun getMostViewed(page: Int, limit: Int): Result<List<Anime>> = safeRemoteList {
        apiService.getMostViewed(page, limit).data
    }

    override suspend fun searchAnimes(query: String, page: Int, limit: Int): Result<List<Anime>> = safeRemoteList {
        apiService.searchAnimes(query, page, limit).data
    }

    override suspend fun searchAdvanced(
        query: String,
        genres: List<String>?,
        status: String?,
        type: String?,
        year: Int?,
        page: Int,
        limit: Int
    ): Result<List<Anime>> = safeRemoteList {
        apiService.searchAdvanced(query, genres?.joinToString(","), status, type, year, page, limit).data
    }

    override suspend fun searchWithFilters(filters: SearchFilters): Result<List<Anime>> = try {
        val request = filters.toApiRequest()
        val response = apiService.searchWithFilters(request)
        Result.success(response.data.map { it.toDomain() })
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun setViewsAnime(animeId: String): Result<Unit> = try {
        apiService.setViewsAnime(animeId)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getLanguagesForAnime(animeId: String): Result<List<Language>> = try {
        val response = apiService.getLanguagesForAnime(animeId)
        Result.success(response.map { it.toDomain() })
    } catch (e: Exception) {
        Result.failure(e)
    }

    override fun observeAnime(id: String): Flow<Anime?> =
        database.animeDao().observeById(id).map { it?.toDomain() }

    override suspend fun getFavorites(): Result<List<Anime>> = try {
        Result.success(database.animeDao().getFavorites().map { it.toDomain() })
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getWatchLater(): Result<List<Anime>> = try {
        Result.success(database.animeDao().getWatchLater().map { it.toDomain() })
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun toggleFavorite(animeId: String, isFavorite: Boolean): Result<Unit> = try {
        database.animeDao().setFavorite(animeId, isFavorite)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun toggleWatchLater(animeId: String, isWatchLater: Boolean): Result<Unit> = try {
        database.animeDao().setWatchLater(animeId, isWatchLater)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun updateLastViewed(animeId: String): Result<Unit> = try {
        database.animeDao().updateLastViewed(animeId, System.currentTimeMillis())
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    private suspend fun <T : Any> safeRemoteList(block: suspend () -> List<T>): Result<List<Anime>> = try {
        val dtos = block()
        val entities = dtos.map { (it as AnimeDto).toEntity() }
        database.animeDao().insertAll(entities)
        Result.success(entities.map { it.toDomain() })
    } catch (e: Exception) {
        Result.failure(e)
    }
}

private fun AnimeDto.toEntity(): AnimeEntity = AnimeEntity(
    id = id,
    name = name,
    nameAlternative = nameAlternative,
    poster = poster,
    banner = banner,
    overview = overview,
    aired = aired,
    type = type,
    status = status,
    genres = Json.encodeToString(genres),
    rating = rating,
    voteAverage = voteAverage,
    season = season,
    broadcast = broadcast,
    prequel = prequel,
    sequel = sequel,
    related = Json.encodeToString(related ?: emptyList()),
    views = views,
    updatedAt = updatedAt,
    languages = Json.encodeToString(languages?.map { it.code } ?: emptyList())
)

private fun AnimeEntity.toDomain(): Anime = Anime(
    id = id,
    name = name,
    nameAlternative = nameAlternative,
    poster = poster,
    banner = banner,
    overview = overview,
    aired = aired,
    type = type,
    status = status,
    genres = Json.decodeFromString<List<String>>(genres),
    rating = rating,
    voteAverage = voteAverage,
    season = season,
    broadcast = broadcast,
    prequel = prequel,
    sequel = sequel,
    related = Json.decodeFromString<List<String>>(related ?: "[]"),
    views = views,
    updatedAt = updatedAt,
    languages = Json.decodeFromString<List<String>>(languages).map { Language(it, it, it) },
    isFavorite = isFavorite,
    isWatchLater = isWatchLater,
    lastViewedAt = lastViewedAt
)

private fun SearchFilters.toApiRequest() = com.darkdeveloper.kawaiianimes.data.api.SearchFiltersRequest(
    query = query,
    genres = genres,
    status = status,
    type = type,
    year = year,
    sort = sort,
    order = order,
    page = page,
    limit = limit
)