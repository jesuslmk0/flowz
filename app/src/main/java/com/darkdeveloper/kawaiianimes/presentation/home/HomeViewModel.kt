package com.darkdeveloper.kawaiianimes.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darkdeveloper.kawaiianimes.domain.model.Anime
import com.darkdeveloper.kawaiianimes.domain.usecase.anime.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@kotlinx.coroutines.android.MainScope
class HomeViewModel @Inject constructor(
    private val getPopularUseCase: GetPopularAnimesUseCase,
    private val getNewAnimesUseCase: GetNewAnimesUseCase,
    private val getRecentAnimesUseCase: GetRecentAnimesUseCase,
    private val getRecommendationsUseCase: GetRecommendationsUseCase,
    private val getWithCastellanoUseCase: GetWithCastellanoUseCase,
    private val getWithLatinoUseCase: GetWithLatinoUseCase,
    private val getCalendarUseCase: GetCalendarUseCase
) : ViewModel() {

    sealed interface UiState {
        data class Loading : UiState
        data class Success(
            val popular: List<Anime> = emptyList(),
            val newAnimes: List<Anime> = emptyList(),
            val recent: List<Anime> = emptyList(),
            val recommendations: List<Anime> = emptyList(),
            val castellano: List<Anime> = emptyList(),
            val latino: List<Anime> = emptyList(),
            val calendar: Map<String, List<Anime>> = emptyMap()
        ) : UiState
        data class Error(val message: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState

    init { loadHome() }

    fun loadHome() = viewModelScope.launch {
        _uiState.value = UiState.Loading
        try {
            val popular = getPopularUseCase(1, 20).getOrNull() ?: emptyList()
            val newAnimes = getNewAnimesUseCase(1, 20).getOrNull() ?: emptyList()
            val recent = getRecentAnimesUseCase(1, 20).getOrNull() ?: emptyList()
            val recommendations = getRecommendationsUseCase(1, 20).getOrNull() ?: emptyList()
            val castellano = getWithCastellanoUseCase(1, 20).getOrNull() ?: emptyList()
            val latino = getWithLatinoUseCase(1, 20).getOrNull() ?: emptyList()
            val calendar = getCalendarUseCase(null, null).getOrNull() ?: emptyMap()

            _uiState.value = UiState.Success(
                popular = popular,
                newAnimes = newAnimes,
                recent = recent,
                recommendations = recommendations,
                castellano = castellano,
                latino = latino,
                calendar = calendar
            )
        } catch (e: Exception) {
            _uiState.value = UiState.Error(e.message ?: "Error cargando home")
        }
    }
}