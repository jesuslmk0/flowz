package com.darkdeveloper.kawaiianimes.domain.repository

import com.darkdeveloper.kawaiianimes.domain.model.Anime
import com.darkdeveloper.kawaiianimes.domain.model.Language
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    suspend fun getAnimeById(id: String): Result<Anime>
    suspend fun getPopularAnimes(page: Int, limit: Int): Result<List<Anime>>
    suspend fun getNewAnimes(page: Int, limit: Int): Result<List<Anime>>
    suspend fun getRecentAnimes(page: Int, limit: Int): Result<List<Anime>>
    suspend fun getRecommendations(page: Int, limit: Int): Result<List<Anime>>
    suspend fun getRelatedAnimes(animeId: String, page: Int, limit: Int): Result<List<Anime>>
    suspend fun getAnimesByGenre(genre: String, page: Int, limit: Int): Result<List<Anime>>
    suspend fun getAnimesByStatus(status: String, page: Int, limit: Int): Result<List<Anime>>
    suspend fun getAnimesByType(type: String, page: Int, limit: Int): Result<List<Anime>>
    suspend fun getMovies(page: Int, limit: Int): Result<List<Anime>>
    suspend fun getCalendar(season: String?, year: Int?): Result<Map<String, List<Anime>>>
    suspend fun getWithCastellano(page: Int, limit: Int): Result<List<Anime>>
    suspend fun getWithLatino(page: Int, limit: Int): Result<List<Anime>>
    suspend fun getMostViewed(page: Int, limit: Int): Result<List<Anime>>
    suspend fun searchAnimes(query: String, page: Int, limit: Int): Result<List<Anime>>
    suspend fun searchAdvanced(
        query: String,
        genres: List<String>?,
        status: String?,
        type: String?,
        year: Int?,
        page: Int,
        limit: Int
    ): Result<List<Anime>>
    suspend fun searchWithFilters(filters: SearchFilters): Result<List<Anime>>
    suspend fun setViewsAnime(animeId: String): Result<Unit>
    suspend fun getLanguagesForAnime(animeId: String): Result<List<Language>>

    fun observeAnime(id: String): Flow<Anime?>
    suspend fun getFavorites(): Result<List<Anime>>
    suspend fun getWatchLater(): Result<List<Anime>>
    suspend fun toggleFavorite(animeId: String, isFavorite: Boolean): Result<Unit>
    suspend fun toggleWatchLater(animeId: String, isWatchLater: Boolean): Result<Unit>
    suspend fun updateLastViewed(animeId: String): Result<Unit>
}

@Serializable
data class SearchFilters(
    val query: String? = null,
    val genres: List<String> = emptyList(),
    val status: String? = null,
    val type: String? = null,
    val year: Int? = null,
    val sort: String = "popularity",
    val order: String = "desc",
    val page: Int = 1,
    val limit: Int = 20
)