package com.darkdeveloper.kawaiianimes.presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.Button
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var autoPlayNext by remember { mutableStateOf(true) }
    var downloadQuality by remember { mutableStateOf("720p") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Section("Reproducción") {
            SettingRow(
                title = "Reproducir siguiente automáticamente",
                subtitle = "Pasa al siguiente episodio al terminar",
                trailing = { Switch(checked = autoPlayNext, onCheckedChange = { autoPlayNext = it }) }
            )
            SettingRow(
                title = "Calidad de descarga",
                subtitle = downloadQuality,
                trailing = { /* TODO: dropdown */ }
            )
        }

        Section("Almacenamiento") {
            SettingRow(
                title = "Limpiar caché",
                subtitle = "Elimina archivos temporales",
                trailing = { Button(onClick = { viewModel.clearCache() }) { Text("Limpiar") } }
            )
        }

        Section("Acerca de") {
            SettingRow(
                title = "Versión",
                subtitle = "1.0.0",
                trailing = {}
            )
            SettingRow(
                title = "Código abierto",
                subtitle = "Ver en GitHub",
                trailing = {}
            )
        }
    }
}

@Composable
fun Section(title: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        androidx.compose.material3.Card(
            modifier = Modifier.fillMaxWidth(),
            colors = androidx.compose.material3.CardDefaults.cardColors(
                containerColor = Color(0xFF1E1E1E)
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                content()
            }
        }
    }
}

@Composable
fun SettingRow(
    title: String,
    subtitle: String,
    trailing: @Composable () -> Unit
) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = title, fontSize = 16.sp, color = Color.White)
            Text(text = subtitle, fontSize = 14.sp, color = Color.White.copy(alpha = 0.6f))
        }
        trailing()
    }
}