package com.darkdeveloper.kawaiianimes.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darkdeveloper.kawaiianimes.domain.model.Anime
import com.darkdeveloper.kawaiianimes.domain.model.User
import com.darkdeveloper.kawaiianimes.domain.repository.AuthRepository
import com.darkdeveloper.kawaiianimes.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@kotlinx.coroutines.android.MainScope
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val animeRepository: AnimeRepository
) : ViewModel() {

    sealed interface UiState {
        data class Loading : UiState
        data class Success(
            val user: User?,
            val favorites: List<Anime> = emptyList(),
            val watchLater: List<Anime> = emptyList()
        ) : UiState
        data class Error(val message: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState

    fun loadProfile() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val userResult = authRepository.getCurrentUser()
                val user = userResult.getOrNull()
                val favoritesResult = animeRepository.getFavorites()
                val favorites = favoritesResult.getOrNull() ?: emptyList()
                val watchLaterResult = animeRepository.getWatchLater()
                val watchLater = watchLaterResult.getOrNull() ?: emptyList()

                _uiState.value = UiState.Success(user = user, favorites = favorites, watchLater = watchLater)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error cargando perfil")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}