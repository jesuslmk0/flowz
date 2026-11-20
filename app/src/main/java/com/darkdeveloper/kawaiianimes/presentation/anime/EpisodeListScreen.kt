package com.darkdeveloper.kawaiianimes.presentation.anime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsStateWithLifecycle
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkdeveloper.kawaiianimes.presentation.common.EmptyState
import com.darkdeveloper.kawaiianimes.presentation.common.ErrorState
import com.darkdeveloper.kawaiianimes.presentation.common.EpisodeItem
import com.darkdeveloper.kawaiianimes.presentation.common.ShimmerAnimeCard
import com.darkdeveloper.kawaiianimes.domain.model.Episode
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon

@Composable
fun EpisodeListScreen(
    animeId: String,
    viewModel: EpisodeListViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    androidx.compose.runtime.LaunchedEffect(animeId) {
        viewModel.loadEpisodes(animeId)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when (uiState) {
            is EpisodeListViewModel.UiState.Loading -> {
                Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    repeat(5) { ShimmerAnimeCard() }
                }
            }
            is EpisodeListViewModel.UiState.Error -> {
                ErrorState(message = uiState.message, onRetry = { viewModel.loadEpisodes(animeId) })
            }
            is EpisodeListViewModel.UiState.Success -> {
                if (uiState.episodes.isEmpty()) {
                    EmptyState(icon = androidx.compose.material.icons.filled.VideoLibrary, message = "No hay episodios")
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(uiState.episodes) { episode ->
                            EpisodeItem(episode = episode, onClick = {
                                // TODO: navigate to player
                            })
                        }
                    }
                }
            }
        }
    }
}