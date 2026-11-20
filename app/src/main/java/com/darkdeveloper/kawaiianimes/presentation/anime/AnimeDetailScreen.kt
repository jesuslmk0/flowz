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
import com.darkdeveloper.kawaiianimes.presentation.common.AnimeCard
import com.darkdeveloper.kawaiianimes.presentation.common.EmptyState
import com.darkdeveloper.kawaiianimes.presentation.common.ErrorState
import com.darkdeveloper.kawaiianimes.presentation.common.EpisodeItem
import com.darkdeveloper.kawaiianimes.presentation.common.ShimmerAnimeCard
import com.darkdeveloper.kawaiianimes.domain.model.Anime
import com.darkdeveloper.kawaiianimes.domain.model.Episode
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon

@Composable
fun AnimeDetailScreen(
    animeId: String,
    viewModel: AnimeDetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    androidx.compose.runtime.LaunchedEffect(animeId) {
        viewModel.loadAnime(animeId)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when (uiState) {
            is AnimeDetailViewModel.UiState.Loading -> {
                Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    ShimmerAnimeCard()
                }
            }
            is AnimeDetailViewModel.UiState.Error -> {
                ErrorState(message = uiState.message, onRetry = { viewModel.loadAnime(animeId) })
            }
            is AnimeDetailViewModel.UiState.Success -> {
                AnimeDetailHeader(uiState.anime)
                EpisodeListSection(uiState.episodes, uiState.anime.id)
            }
        }
    }
}

@Composable
fun AnimeDetailHeader(anime: Anime) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Text(
            text = anime.displayName,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.foundation.layout.padding(top = 8.dp))
        Text(
            text = "${anime.type.name} • ${anime.status.name} • ${anime.primaryGenre} • ${anime.rating}/10",
            fontSize = 14.sp,
            color = Color.White.copy(alpha = 0.7f)
        )
        anime.overview?.let { overview ->
            androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.foundation.layout.padding(top = 12.dp))
            Text(
                text = overview,
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.9f)
            )
        }
    }
}

@Composable
fun EpisodeListSection(episodes: List<Episode>, animeId: String) {
    if (episodes.isEmpty()) {
        EmptyState(icon = androidx.compose.material.icons.filled.VideoLibrary, message = "No hay episodios disponibles")
    } else {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Episodios (${episodes.size})",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(episodes) { episode ->
                    EpisodeItem(episode = episode, onClick = {
                        // TODO: navigate to player with episode
                    })
                }
            }
        }
    }
}