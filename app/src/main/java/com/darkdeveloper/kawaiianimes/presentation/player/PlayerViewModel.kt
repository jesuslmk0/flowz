package com.darkdeveloper.kawaiianimes.presentation.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darkdeveloper.kawaiianimes.data.extractors.ExtractResult
import com.darkdeveloper.kawaiianimes.data.extractors.ExtractorRegistry
import com.darkdeveloper.kawaiianimes.domain.model.Anime
import com.darkdeveloper.kawaiianimes.domain.model.Episode
import com.darkdeveloper.kawaiianimes.domain.model.Server
import com.darkdeveloper.kawaiianimes.domain.usecase.player.BuildPlayerUrlUseCase
import com.darkdeveloper.kawaiianimes.domain.usecase.player.GetServersUseCase
import com.darkdeveloper.kawaiianimes.domain.usecase.player.GetPlayersForEpisodeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayerViewModel @Inject constructor(
    private val getServersUseCase: GetServersUseCase,
    private val getPlayersForEpisodeUseCase: GetPlayersForEpisodeUseCase,
    private val buildPlayerUrlUseCase: BuildPlayerUrlUseCase,
    private val extractorRegistry: ExtractorRegistry
) : ViewModel() {

    sealed interface UiState {
        data class Loading : UiState
        data class Ready(
            val anime: Anime,
            val episode: Episode,
            val servers: List<Server>,
            val selectedServer: Server?,
            val selectedLanguage: String
        ) : UiState
        data class Extracting(val server: Server) : UiState
        data class Playing(val server: Server, val playableUrl: String) : UiState
        data class Error(val message: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState

    fun loadEpisode(anime: Anime, episode: Episode, language: String = "sub") {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val result = getServersUseCase(anime.id, episode.number, language)
            result.fold(
                onSuccess = { servers ->
                    val preferredServer = servers.firstOrNull { it.language == language }
                        ?: servers.firstOrNull()
                    _uiState.value = UiState.Ready(
                        anime = anime,
                        episode = episode,
                        servers = servers,
                        selectedServer = preferredServer,
                        selectedLanguage = language
                    )
                    if (preferredServer != null) {
                        extractAndPlay(preferredServer!!)
                    }
                },
                onFailure = { error ->
                    _uiState.value = UiState.Error("Error cargando servidores: ${error.message}")
                }
            )
        }
    }

    fun selectServer(server: Server) {
        _uiState.value = (uiState.value as? UiState.Ready)?.copy(selectedServer = server)
            ?: return
        if (server.isExtracted) {
            _uiState.value = UiState.Playing(anime = anime, episode = episode, server = server, playableUrl = server.videoUrl!!)
        } else {
            extractAndPlay(server)
        }
    }

    private val anime: Anime
        get() = (uiState.value as? UiState.Ready)?.anime ?: throw IllegalStateException("Anime not loaded")
    private val episode: Episode
        get() = (uiState.value as? UiState.Ready)?.episode ?: throw IllegalStateException("Episode not loaded")

    private fun extractAndPlay(server: Server) {
        viewModelScope.launch {
            _uiState.value = UiState.Extracting(server)
            val result = buildPlayerUrlUseCase.execute(server, episode.number, selectedLanguage)
            result.fold(
                onSuccess = { playableUrl ->
                    val updatedServer = server.copy(videoUrl = playableUrl, extractedAt = System.currentTimeMillis())
                    _uiState.value = UiState.Playing(
                        anime = anime,
                        episode = episode,
                        server = updatedServer,
                        playableUrl = playableUrl
                    )
                },
                onFailure = { error ->
                    _uiState.value = UiState.Error("Error extrayendo video: ${error.message}")
                }
            )
        }
    }

    var selectedLanguage: String = "sub"
        private set
}