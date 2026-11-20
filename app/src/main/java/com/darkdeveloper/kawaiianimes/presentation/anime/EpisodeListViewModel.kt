package com.darkdeveloper.kawaiianimes.presentation.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darkdeveloper.kawaiianimes.domain.model.Episode
import com.darkdeveloper.kawaiianimes.domain.usecase.anime.GetEpisodesForAnimeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@kotlinx.coroutines.android.MainScope
class EpisodeListViewModel @Inject constructor(
    private val getEpisodesUseCase: GetEpisodesForAnimeUseCase
) : ViewModel() {

    sealed interface UiState {
        data class Loading : UiState
        data class Success(val episodes: List<Episode>) : UiState
        data class Error(val message: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState

    fun loadEpisodes(animeId: String, language: String? = null) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val result = getEpisodesUseCase(animeId, language)
                val episodes = result.getOrNull() ?: emptyList()
                _uiState.value = UiState.Success(episodes)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error cargando episodios")
            }
        }
    }
}