package com.darkdeveloper.kawaiianimes.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import com.darkdeveloper.kawaiianimes.presentation.common.AnimeCard
import com.darkdeveloper.kawaiianimes.presentation.common.EmptyState
import com.darkdeveloper.kawaiianimes.presentation.common.ErrorState
import com.darkdeveloper.kawaiianimes.presentation.common.ShimmerAnimeCard
import com.darkdeveloper.kawaiianimes.domain.model.Anime
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Buscar anime...", color = Color.White.copy(alpha = 0.5f)) },
            leadingIcon = { Icon(Search, contentDescription = "Search", tint = Color.White.copy(alpha = 0.5f)) },
            colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color(0xFF1E1E1E),
                focusedContainerColor = Color(0xFF2C2C2C),
                unfocusedBorderColor = Color.White.copy(alpha = 0.2f),
                focusedBorderColor = Color(0xFF7C4DFF),
                placeholderTextColor = Color.White.copy(alpha = 0.5f),
                textColor = Color.White,
                cursorColor = Color(0xFF7C4DFF)
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { viewModel.search(query) })
        )

        when (uiState) {
            is SearchViewModel.UiState.Loading -> {
                Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    repeat(3) { ShimmerAnimeCard() }
                }
            }
            is SearchViewModel.UiState.Success -> {
                if (uiState.results.isEmpty()) {
                    EmptyState(message = "No se encontraron resultados para: ${uiState.query}")
                } else {
                    androidx.compose.foundation.lazy.LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(uiState.results) { anime ->
                            AnimeCard(anime = anime, onClick = { /* TODO: navigate to detail */ })
                        }
                    }
                }
            }
            is SearchViewModel.UiState.Empty -> {
                EmptyState(message = "No se encontraron resultados para: ${uiState.query}")
            }
            is SearchViewModel.UiState.Error -> {
                ErrorState(message = uiState.message, onRetry = { viewModel.search(query) })
            }
        }
    }
}