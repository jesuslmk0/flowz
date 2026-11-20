package com.darkdeveloper.kawaiianimes.presentation.player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsStateWithLifecycle
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkdeveloper.kawaiianimes.presentation.common.ErrorState
import com.darkdeveloper.kawaiianimes.presentation.common.VideoPlayer
import com.darkdeveloper.kawaiianimes.domain.model.Server
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon

@Composable
fun PlayerScreen(
    animeId: String,
    episodeNumber: Int,
    viewModel: PlayerViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is PlayerViewModel.UiState.Loading -> {
                androidx.compose.foundation.layout.Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    androidx.compose.material3.CircularProgressIndicator(
                        color = Color(0xFF7C4DFF),
                        modifier = Modifier.size(48.dp)
                    )
                    androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.foundation.layout.padding(16.dp))
                    Text("Cargando...", color = Color.White, fontSize = 16.sp)
                }
            }
            is PlayerViewModel.UiState.Ready -> {
                PlayerScreenContent(uiState)
            }
            is PlayerViewModel.UiState.Extracting -> {
                androidx.compose.foundation.layout.Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    androidx.compose.material3.CircularProgressIndicator(
                        color = Color(0xFF00E676),
                        modifier = Modifier.size(48.dp)
                    )
                    androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.foundation.layout.padding(16.dp))
                    Text("Extrayendo video de ${uiState.server.name}...", color = Color.White, fontSize = 16.sp)
                }
            }
            is PlayerViewModel.UiState.Playing -> {
                VideoPlayer(
                    server = uiState.server,
                    onError = { msg -> /* TODO: handle error */ },
                    onPrepared = { /* TODO: handle prepared */ }
                )
            }
            is PlayerViewModel.UiState.Error -> {
                ErrorState(message = uiState.message, onRetry = {
                    // TODO: retry logic
                })
            }
        }
    }
}

@Composable
fun PlayerScreenContent(state: PlayerViewModel.UiState.Ready) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "${state.anime.displayName} - Episodio ${state.episode.formattedNumber}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = androidx.compose.ui.text.TextAlign.Center,
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        )

        Text(
            text = "Selecciona un servidor",
            fontSize = 14.sp,
            color = Color.White.copy(alpha = 0.7f)
        )

        // Server list would go here
        // For now show first server
        state.servers.firstOrNull()?.let { server ->
            Text(
                text = server.displayName,
                fontSize = 16.sp,
                color = Color(0xFF00E676),
                fontWeight = FontWeight.Medium
            )
        }
    }
}