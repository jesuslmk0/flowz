package com.darkdeveloper.kawaiianimes.presentation.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darkdeveloper.kawaiianimes.domain.model.Anime
import com.darkdeveloper.kawaiianimes.domain.model.Episode
import com.darkdeveloper.kawaiianimes.domain.usecase.anime.GetAnimeByIdUseCase
import com.darkdeveloper.kawaiianimes.domain.usecase.anime.GetEpisodesForAnimeUseCase
import com.darkdeveloper.kawaiianimes.domain.usecase.anime.GetLanguagesForAnimeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@kotlinx.coroutines.android.MainScope
class AnimeDetailViewModel @Inject constructor(
    private val getAnimeUseCase: GetAnimeByIdUseCase,
    private val getEpisodesUseCase: GetEpisodesForAnimeUseCase,
    private val getLanguagesUseCase: GetLanguagesForAnimeUseCase
) : ViewModel() {

    sealed interface UiState {
        data class Loading : UiState
        data class Success(
            val anime: Anime,
            val episodes: List<Episode> = emptyList(),
            val languages: List<String> = emptyList()
        ) : UiState
        data class Error(val message: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState

    fun loadAnime(animeId: String, language: String? = null) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val animeResult = getAnimeUseCase(animeId)
                val anime = animeResult.getOrThrow()
                val episodesResult = getEpisodesUseCase(animeId, language)
                val episodes = episodesResult.getOrNull() ?: emptyList()
                val languagesResult = getLanguagesUseCase(animeId)
                val languages = languagesResult.getOrNull() ?: emptyList()

                _uiState.value = UiState.Success(anime = anime, episodes = episodes, languages = languages)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error cargando anime")
            }
        }
    }

    fun toggleFavorite(animeId: String, isFavorite: Boolean) {
        // TODO: implement favorite toggle
    }

    fun toggleWatchLater(animeId: String, isWatchLater: Boolean) {
        // TODO: implement watch later toggle
    }
}