package com.darkdeveloper.kawaiianimes.data.remoteconfig

import com.darkdeveloper.kawaiianimes.data.api.KawaiiApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteConfigRepository @Inject constructor(
    private val apiService: KawaiiApiService
) {

    private val _config = MutableStateFlow<RemoteConfig>(RemoteConfig())
    val config = _config.asStateFlow()

    data class RemoteConfig(
        val apiBaseUrl: String = "https://api.kawaiianimes.app/api/v4",
        val cdnBaseUrl: String = "https://cdn.kawaiianimes.app",
        val minAppVersion: Int = 1,
        val forceUpdate: Boolean = false,
        val maintenanceMode: Boolean = false,
        val features: Map<String, Boolean> = emptyMap()
    )

    suspend fun fetchConfig() {
        // TODO: Implement remote config fetch from API
        // For now use defaults
    }
}