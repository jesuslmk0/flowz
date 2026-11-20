package com.darkdeveloper.kawaiianimes.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.ui.PlayerView
import com.darkdeveloper.kawaiianimes.domain.model.Server
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun VideoPlayer(
    server: Server,
    onError: (String) -> Unit,
    onPrepared: (ExoPlayer) -> Unit,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .setMediaSourceFactory(
                DefaultMediaSourceFactory(
                    AdBlockingDataSourceFactory(context, DefaultHttpDataSource.Factory()
                        .setDefaultRequestProperties(
                            mapOf<String, String>(
                                "User-Agent" to "Mozilla/5.0 (Linux; Android 13; Mobile) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36",
                                "Accept" to "*/*",
                                "Accept-Language" to "es-ES,es;q=0.9,en;q=0.8",
                                "Referer" to (server.referer ?: server.embedUrl) as String,
                                "Origin" to (server.embedUrl.takeUnless { it.isBlank() } ?: "") as String
                            ).filterValues { it.isNotBlank() }
                        )
                    )
                )
            )
            .build()
    }
    val isPrepared = remember { mutableStateOf(false) }

    androidx.compose.runtime.LaunchedEffect(server.videoUrl, server.embedUrl) {
        val playableUrl = server.videoUrl ?: server.embedUrl
        if (playableUrl.isNotBlank()) {
            val mediaItem = MediaItem.fromUri(playableUrl)
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
            isPrepared.value = true
            onPrepared(exoPlayer)
        }
    }

    androidx.compose.runtime.DisposableEffect(exoPlayer) {
        onDispose { exoPlayer.release() }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    useController = true
                    controllerShowTimeoutMs = 5000
                    layoutParams = android.view.ViewGroup.LayoutParams(
                        android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                        android.view.ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            },
            modifier = modifier
        )
    }
}