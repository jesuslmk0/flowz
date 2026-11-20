package com.darkdeveloper.kawaiianimes.presentation.common

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShimmerAnimeCard(modifier: Modifier = Modifier) {
    val progress = remember {
        mutableStateOf(0f)
    }
    androidx.compose.runtime.LaunchedEffect(Unit) {
        progress.value = 1f
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            val colors = listOf(
                Color(0xFF2C2C2C),
                Color(0xFF3C3C3C),
                Color(0xFF2C2C2C)
            )
            val shader = Brush.linearGradient(
                colors = colors,
                start = Offset(x = -progress.value * 300, y = 0f),
                end = Offset(x = 300f - progress.value * 300, y = 0f)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { this.ambientShadowAlpha = 0f; this.spotShadowAlpha = 0f }
                    .background(shader)
            )
        }
    }
}

@Composable
fun ShimmerEpisodeItem(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .clip(RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF2C2C2C), Color(0xFF3C3C3C), Color(0xFF2C2C2C)),
                        start = Offset.Zero,
                        end = Offset(300f, 0f)
                    )
                )
        )
    }
}