package com.darkdeveloper.kawaiianimes.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.darkdeveloper.kawaiianimes.presentation.common.ShimmerAnimeCard
import com.darkdeveloper.kawaiianimes.domain.model.Anime
import androidx.compose.material.icons.filled.Search

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        when (uiState) {
            is HomeViewModel.UiState.Loading -> {
                repeat(3) { ShimmerAnimeCard() }
            }
            is HomeViewModel.UiState.Error -> {
                ErrorState(message = uiState.message, onRetry = { viewModel.loadHome() })
            }
            is HomeViewModel.UiState.Success -> {
                AnimeSection("Populares", uiState.popular)
                AnimeSection("Nuevos", uiState.newAnimes)
                AnimeSection("Recientes", uiState.recent)
                AnimeSection("Recomendados", uiState.recommendations)
                AnimeSection("Castellano", uiState.castellano)
                AnimeSection("Latino", uiState.latino)
            }
        }
    }
}

@Composable
fun AnimeSection(title: String, animes: List<Anime>) {
    if (animes.isNotEmpty()) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 4.dp)
            )
            androidx.compose.foundation.lazy.LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(animes) { anime ->
                    AnimeCard(anime = anime, onClick = { /* TODO: navigate to detail */ })
                }
            }
        }
    }
}