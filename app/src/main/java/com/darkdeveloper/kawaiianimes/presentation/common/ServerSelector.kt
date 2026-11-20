package com.darkdeveloper.kawaiianimes.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darkdeveloper.kawaiianimes.domain.model.Server

@Composable
fun ServerSelector(
    servers: List<Server>,
    selectedServer: Server?,
    onSelect: (Server) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier.padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedServer?.displayName ?: "Seleccionar servidor",
                fontSize = 16.sp,
                color = Color.White
            )
            Icon(
                imageVector = if (expanded) ExpandLess else ExpandMore,
                contentDescription = if (expanded) "Collapse" else "Expand",
                tint = Color.White
            )
        }

        if (expanded) {
            Column {
                servers.forEach { server ->
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
                            text = server.displayName,
                            fontSize = 14.sp,
                            color = if (server == selectedServer) Color(0xFF00E676) else Color.White
                        )
                        if (server.isPremium) {
                            Text(
                                text = "PREMIUM",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFFD700)
                            )
                        }
                        if (server == selectedServer) {
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