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
fun LanguageSelector(
    servers: List<Server>,
    selectedServer: Server?,
    onSelect: (Server) -> Unit,
    modifier: Modifier = Modifier
) {
    val languages = servers.map { it.language }.distinct()

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Idioma",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.foundation.layout.padding(8.dp))
        Column {
            languages.forEach { lang ->
                val serversWithLang = servers.filter { it.language == lang }
                serversWithLang.firstOrNull()?.let { server ->
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
                            text = lang.uppercase(),
                            fontSize = 14.sp,
                            color = if (selectedServer?.language == lang) Color(0xFF00E676) else Color.White
                        )
                        if (selectedServer?.language == lang) {
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