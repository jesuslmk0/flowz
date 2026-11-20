package com.darkdeveloper.kawaiianimes.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.darkdeveloper.kawaiianimes.domain.model.Anime
import com.darkdeveloper.kawaiianimes.domain.repository.SearchFilters
import com.darkdeveloper.kawaiianimes.domain.usecase.anime.SearchAnimesUseCase
import com.darkdeveloper.kawaiianimes.domain.usecase.anime.SearchAdvancedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@kotlinx.coroutines.android.MainScope
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchAnimesUseCase,
    private val advancedSearchUseCase: SearchAdvancedUseCase
) : ViewModel() {

    sealed interface UiState {
        data class Loading : UiState
        data class Success(val results: List<Anime> = emptyList(), val query: String = "") : UiState
        data class Empty(val query: String) : UiState
        data class Error(val message: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Empty(""))
    val uiState = _uiState

    fun search(query: String) {
        viewModelScope.launch {
            if (query.isBlank()) {
                _uiState.value = UiState.Empty(query)
                return@launch
            }
            _uiState.value = UiState.Loading
            try {
                val results = searchUseCase(query, 1, 20).getOrNull() ?: emptyList()
                if (results.isEmpty()) {
                    _uiState.value = UiState.Empty(query)
                } else {
                    _uiState.value = UiState.Success(results, query)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error en búsqueda")
            }
        }
    }

    fun searchAdvanced(
        query: String,
        genres: List<String>? = null,
        status: String? = null,
        type: String? = null,
        year: Int? = null
    ) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val results = advancedSearchUseCase(query, genres, status, type, year, 1, 20).getOrNull() ?: emptyList()
                if (results.isEmpty()) {
                    _uiState.value = UiState.Empty(query)
                } else {
                    _uiState.value = UiState.Success(results, query)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Error en búsqueda avanzada")
            }
        }
    }
}