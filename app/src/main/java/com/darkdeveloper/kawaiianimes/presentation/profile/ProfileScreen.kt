package com.darkdeveloper.kawaiianimes.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.Button
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
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.Icon

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    androidx.compose.runtime.LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        when (uiState) {
            is ProfileViewModel.UiState.Loading -> {
                androidx.compose.foundation.layout.Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    androidx.compose.material3.CircularProgressIndicator(color = Color(0xFF7C4DFF))
                }
            }
            is ProfileViewModel.UiState.Error -> {
                ErrorState(message = uiState.message, onRetry = { viewModel.loadProfile() })
            }
            is ProfileViewModel.UiState.Success -> {
                if (uiState.user != null) {
                    UserHeader(uiState.user!!, onLogout = { viewModel.logout() })
                }

                if (uiState.favorites.isNotEmpty()) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Favoritos", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(uiState.favorites) { anime ->
                                AnimeCard(anime = anime, onClick = { /* TODO */ })
                            }
                        }
                    }
                }

                if (uiState.watchLater.isNotEmpty()) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Ver después", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(uiState.watchLater) { anime ->
                                AnimeCard(anime = anime, onClick = { /* TODO */ })
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UserHeader(
    user: com.darkdeveloper.kawaiianimes.domain.model.User,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = user.username,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = user.email,
            fontSize = 14.sp,
            color = Color.White.copy(alpha = 0.7f)
        )
        user.avatar?.let { avatar ->
            androidx.compose.foundation.Image(
                painter = androidx.compose.foundation.Image(
                    painter = coil.compose.AsyncImage(
                        model = avatar,
                        contentDescription = user.username,
                        modifier = Modifier.size(80.dp).clip(androidx.compose.foundation.shape.CircleShape)
                    ).painter
                ),
                contentDescription = user.username,
                modifier = Modifier.size(80.dp).clip(androidx.compose.foundation.shape.CircleShape)
            )
        }
        if (user.isPremium) {
            Text(
                text = "PREMIUM",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFD700)
            )
        }
        Button(onClick = onLogout) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Logout, contentDescription = "Logout", tint = Color.White)
                androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.foundation.layout.padding(8.dp))
                Text("Cerrar sesión", color = Color.White)
            }
        }
    }
}