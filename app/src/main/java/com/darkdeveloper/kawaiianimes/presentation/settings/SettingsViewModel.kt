package com.darkdeveloper.kawaiianimes.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@kotlinx.coroutines.android.MainScope
class SettingsViewModel @Inject constructor() : ViewModel() {

    sealed interface UiState {
        data class Success : UiState
        data class Error(val message: String) : UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Success)
    val uiState = _uiState

    fun clearCache() {
        // TODO: implement cache clearing
    }
}