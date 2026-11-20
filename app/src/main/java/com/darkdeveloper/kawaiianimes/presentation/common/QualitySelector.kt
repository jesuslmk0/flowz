package com.darkdeveloper.kawaiianimes.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkdeveloper.kawaiianimes.domain.model.Server

@Composable
fun QualitySelector(
    servers: List<Server>,
    selectedServer: Server?,
    onSelect: (Server) -> Unit,
    modifier: Modifier = Modifier
) {
    val qualities = servers.map { it.quality ?: 0 }.distinct().sortedDescending()

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Calidad",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.foundation.layout.padding(8.dp))
        Column {
            qualities.forEach { quality ->
                val serversWithQuality = servers.filter { it.quality == quality }
                serversWithQuality.firstOrNull()?.let { server ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .fillMaxWidth()
                            .clickable { onSelect(server) },
                        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${quality}p",
                            fontSize = 14.sp,
                            color = if (selectedServer?.quality == quality) Color(0xFF00E676) else Color.White
                        )
                        if (selectedServer?.quality == quality) {
                            Icon(
                                imageVector = androidx.compose.material.icons.filled.Check,
                                contentDescription = "Selected",
                                tint = Color(0xFF00E676)
                            )
                        }
                    }
                }
            }
        }
    }
}