package com.darkdeveloper.kawaiianimes.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkdeveloper.kawaiianimes.domain.model.Episode

@Composable
fun EpisodeItem(
    episode: Episode,
    isCurrent: Boolean = false,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bgColor = if (isCurrent) Color(0xFF7C4DFF).copy(alpha = 0.2f) else Color.Transparent

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(bgColor)
            .fillMaxWidth()
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Episodio ${episode.formattedNumber}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
            episode.title?.let { title ->
                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.7f),
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.TextOverflow.Ellipsis
                )
            }
        }

        Text(
            text = "${episode.languages.joinToString(", ") { it.displayName }} • ${episode.servers.size} servidores",
            fontSize = 12.sp,
            color = Color.White.copy(alpha = 0.6f)
        )
    }
}