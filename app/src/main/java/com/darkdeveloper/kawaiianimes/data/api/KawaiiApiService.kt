package com.darkdeveloper.kawaiianimes.data.api

import com.darkdeveloper.kawaiianimes.data.api.dto.*
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.queryParameters
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.Url
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString

class KawaiiApiService(private val client: HttpClient) {

    private val baseUrl = "https://api.kawaiianimes.app/api/v4"

    private suspend fun <T> request(method: HttpMethod, path: String, queryParams: List<Pair<String, String>> = emptyList(), body: Any? = null): T {
        return client.request<HttpResponse> {
            this.method = method
            url("$baseUrl$path") {
                queryParameters.appendAll(queryParams)
            }
            if (body != null) {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
        }.body()
    }

    private suspend fun <T> get(path: String, queryParams: List<Pair<String, String>> = emptyList()): T {
        return request(HttpMethod.Get, path, queryParams)
    }

    private suspend fun <T> post(path: String, body: Any? = null, queryParams: List<Pair<String, String>> = emptyList()): T {
        return request(HttpMethod.Post, path, queryParams, body)
    }

    // Anime endpoints
    suspend fun getAnimeById(id: String): AnimeDto = get("/anime/$id")

    suspend fun getPopularAnimes(page: Int = 1, limit: Int = 20): AnimeListResponse =
        get("/anime/popular", listOf("page" to page.toString(), "limit" to limit.toString()))

    suspend fun getNewAnimes(page: Int = 1, limit: Int = 20): AnimeListResponse =
        get("/anime/new", listOf("page" to page.toString(), "limit" to limit.toString()))

    suspend fun getRecentAnimes(page: Int = 1, limit: Int = 20): AnimeListResponse =
        get("/anime/recent", listOf("page" to page.toString(), "limit" to limit.toString()))

    suspend fun getRecommendations(page: Int = 1, limit: Int = 20): AnimeListResponse =
        get("/anime/recommendations", listOf("page" to page.toString(), "limit" to limit.toString()))

    suspend fun getRelatedAnimes(id: String, page: Int = 1, limit: Int = 20): AnimeListResponse =
        get("/anime/related/$id", listOf("page" to page.toString(), "limit" to limit.toString()))

    suspend fun getAnimesByGenre(genre: String, page: Int = 1, limit: Int = 20): AnimeListResponse =
        get("/anime", listOf("genre" to genre, "page" to page.toString(), "limit" to limit.toString()))

    suspend fun getAnimesByStatus(status: String, page: Int = 1, limit: Int = 20): AnimeListResponse =
        get("/anime", listOf("status" to status, "page" to page.toString(), "limit" to limit.toString()))

    suspend fun getAnimesByType(type: String, page: Int = 1, limit: Int = 20): AnimeListResponse =
        get("/anime", listOf("type" to type, "page" to page.toString(), "limit" to limit.toString()))

    suspend fun getMovies(page: Int = 1, limit: Int = 20): AnimeListResponse =
        get("/anime/movies", listOf("page" to page.toString(), "limit" to limit.toString()))

    suspend fun getCalendar(season: String? = null, year: Int? = null): CalendarResponse =
        get("/anime/calendar", buildList {
            if (season != null) add("season" to season)
            if (year != null) add("year" to year.toString())
        })

    suspend fun getWithCastellano(page: Int = 1, limit: Int = 20): AnimeListResponse =
        get("/anime", listOf("dub" to "castellano", "page" to page.toString(), "limit" to limit.toString()))

    suspend fun getWithLatino(page: Int = 1, limit: Int = 20): AnimeListResponse =
        get("/anime", listOf("dub" to "latino", "page" to page.toString(), "limit" to limit.toString()))

    suspend fun getMostViewed(page: Int = 1, limit: Int = 20): AnimeListResponse =
        get("/anime/most-viewed", listOf("page" to page.toString(), "limit" to limit.toString()))

    suspend fun searchAnimes(query: String, page: Int = 1, limit: Int = 20): AnimeListResponse =
        get("/anime/search", listOf("q" to query, "page" to page.toString(), "limit" to limit.toString()))

    suspend fun searchAdvanced(
        query: String,
        genres: String? = null,
        status: String? = null,
        type: String? = null,
        year: Int? = null,
        page: Int = 1,
        limit: Int = 20
    ): AnimeListResponse = get("/anime/search/advanced", buildList {
        add("q" to query)
        if (genres != null) add("genres" to genres)
        if (status != null) add("status" to status)
        if (type != null) add("type" to type)
        if (year != null) add("year" to year.toString())
        add("page" to page.toString())
        add("limit" to limit.toString())
    })

    suspend fun searchWithFilters(request: SearchFiltersRequest): AnimeListResponse =
        post("/anime/search/filters", request)

    suspend fun setViewsAnime(animeId: String): Unit =
        post("/anime/views", mapOf("anime_id" to animeId))

    suspend fun getLanguagesForAnime(id: String): List<LanguageDto> =
        get("/anime/$id/languages")

    suspend fun getEpisodesForAnime(id: String, lang: String? = null): EpisodeListResponse =
        get("/anime/$id/episodes", buildList { if (lang != null) add("lang" to lang) })

    suspend fun getEpisode(id: String): EpisodeDto = get("/episode/$id")

    suspend fun getServers(animeId: String, episode: Int, lang: String): List<ServerDto> =
        get("/api/videos/", listOf("anime_id" to animeId, "episode" to episode.toString(), "lang" to lang))

    // Auth endpoints
    suspend fun register(request: RegisterRequest): AuthResponseDto =
        post("/auth/register", request)

    suspend fun login(request: LoginRequest): AuthResponseDto =
        post("/auth/token", request)

    suspend fun logout(): Unit = post("/auth/logout")

    suspend fun getUser(): UserDto = get("/auth/user")

    suspend fun updateUser(request: UpdateUserRequest): UserDto =
        post("/auth/user/update", request)

    suspend fun requestPasswordCode(request: PasswordCodeRequest): Unit =
        post("/auth/password/code", request)

    suspend fun restorePassword(request: PasswordRestoreRequest): Unit =
        post("/auth/password/restore", request)

    suspend fun getFavorites(): List<String> = get("/auth/favorites")

    suspend fun addFavorite(request: FavoriteRequest): Unit =
        post("/auth/favorites/add", request)

    suspend fun removeFavorite(request: FavoriteRequest): Unit =
        post("/auth/favorites/remove", request)

    suspend fun getWatchLater(): List<String> = get("/auth/watchlater")

    suspend fun addWatchLater(request: WatchLaterRequest): Unit =
        post("/auth/watchlater/add", request)

    suspend fun removeWatchLater(request: WatchLaterRequest): Unit =
        post("/auth/watchlater/remove", request)

    suspend fun getWatching(): List<String> = get("/auth/watching")

    suspend fun addWatching(request: WatchingRequest): Unit =
        post("/auth/watching/add", request)

    suspend fun removeWatching(request: WatchingRequest): Unit =
        post("/auth/watching/remove", request)

    suspend fun getAllLists(): List<ListItemDto> = get("/auth/lists/all")

    suspend fun syncLists(request: ListSyncRequest): ListDeltaResponse =
        post("/auth/lists/sync", request)

    suspend fun restoreFromApp(request: RestoreFromAppRequest): ListDeltaResponse =
        post("/auth/lists/restore-from-app", request)

    suspend fun updateProgress(request: ProgressRequest): Unit =
        post("/auth/progress/update", request)

    suspend fun deleteAnimeProgress(request: DeleteProgressRequest): Unit =
        post("/auth/progress/delete-anime", request)

    suspend fun getPointsStatus(): PointsResponseDto = get("/auth/points/status")

    suspend fun earnPoints(request: EarnPointsRequest): Unit =
        post("/auth/points/earn", request)

    suspend fun spendPoints(request: SpendPointsRequest): Unit =
        post("/auth/points/spend", request)

    suspend fun getPremiumEntitlement(): PremiumEntitlementDto = get("/auth/premium/entitlement")

    suspend fun renewPremium(request: RenewPremiumRequest): Unit =
        post("/auth/premium/renew", request)

    suspend fun getViews(): List<String> = get("/auth/views")

    suspend fun addView(request: ViewRequest): Unit =
        post("/auth/views/add", request)

    suspend fun removeView(request: ViewRequest): Unit =
        post("/auth/views/remove", request)

    suspend fun sync(): SyncResponse = post("/sync")

    suspend fun bulkSync(request: BulkSyncRequest): SyncResponse =
        post("/bulk-sync", request)

    suspend fun createMpPreference(request: MpPreferenceRequest): MpPreferenceResponse =
        post("/mp/create-preference", request)
}

@Serializable
data class AnimeListResponse(
    val data: List<AnimeDto>,
    val pagination: PaginationInfo
)

@Serializable
data class PaginationInfo(
    val currentPage: Int,
    val totalPages: Int,
    val totalItems: Long,
    val perPage: Int
)

@Serializable
data class EpisodeListResponse(
    val data: List<EpisodeDto>,
    val pagination: PaginationInfo
)

@Serializable
data class CalendarResponse(
    val data: Map<String, List<AnimeDto>>
)

@Serializable
data class SearchFiltersRequest(
    val query: String?,
    val genres: List<String>?,
    val status: String?,
    val type: String?,
    val year: Int?,
    val sort: String?,
    val order: String?,
    val page: Int,
    val limit: Int
)

@Serializable
data class RegisterRequest(
    val email: String,
    val password: String,
    val username: String
)

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class UpdateUserRequest(
    val username: String?,
    val avatar: String?,
    val theme: String?
)

@Serializable
data class PasswordCodeRequest(val email: String)
@Serializable
data class PasswordRestoreRequest(val email: String, val code: String, val password: String)

@Serializable
data class FavoriteRequest(val animeId: String)
@Serializable
data class WatchLaterRequest(val animeId: String)
@Serializable
data class WatchingRequest(val animeId: String, val episode: Int)
@Serializable
data class ViewRequest(val animeId: String)

@Serializable
data class ListSyncRequest(
    val favorites: List<String>,
    val watchLater: List<String>,
    val watching: Map<String, Int>,
    val views: List<String>
)

@Serializable
data class RestoreFromAppRequest(
    val backupData: String
)

@Serializable
data class ListDeltaResponse(
    val added: List<String>,
    val removed: List<String>,
    val updated: Map<String, Any>
)

@Serializable
data class ProgressRequest(
    val animeId: String,
    val episode: Int,
    val progress: Float,
    val timestamp: Long
)

@Serializable
data class DeleteProgressRequest(val animeId: String)

@Serializable
data class EarnPointsRequest(val amount: Int, val source: String)
@Serializable
data class SpendPointsRequest(val amount: Int, val target: String)

@Serializable
data class RenewPremiumRequest(val receipt: String, val productId: String)

@Serializable
data class MpPreferenceRequest(val items: List<String>)
@Serializable
data class SyncResponse(val success: Boolean, val message: String?)
@Serializable
data class BulkSyncRequest(val data: String)

@Serializable
data class LanguageDto(
    val code: String,
    val name: String,
    val nativeName: String
)