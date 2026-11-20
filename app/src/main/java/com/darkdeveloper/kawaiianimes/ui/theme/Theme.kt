package com.darkdeveloper.kawaiianimes.ui.theme

import android.content.Context
import androidx.annotation.ColorRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF9B8AFF),
    primaryContainer = Color(0xFF2A2440),
    secondary = Color(0xFFB8AFFF),
    secondaryContainer = Color(0xFF1E1A2E),
    tertiary = Color(0xFFC4B5FD),
    tertiaryContainer = Color(0xFF1A1628),
    background = Color(0xFF0A0A0A),
    surface = Color(0xFF121212),
    surfaceVariant = Color(0xFF1A1A1A),
    surfaceContainerLow = Color(0xFF121212),
    surfaceContainer = Color(0xFF141414),
    surfaceContainerHigh = Color(0xFF1A1A1A),
    surfaceContainerHighest = Color(0xFF1E1E1E),
    error = Color(0xFFF4A5A5),
    errorContainer = Color(0xFF3A1A1A),
    onPrimary = Color(0xFF0A0A0A),
    onPrimaryContainer = Color(0xFFE8E0FF),
    onSecondary = Color(0xFF0A0A0A),
    onSecondaryContainer = Color(0xFFE8E0FF),
    onTertiary = Color(0xFF0A0A0A),
    onTertiaryContainer = Color(0xFFE8E0FF),
    onBackground = Color(0xFFF5F5F5),
    onSurface = Color(0xFFE8E8E8),
    onSurfaceVariant = Color(0xFFA0A0A0),
    onError = Color(0xFF0A0A0A),
    onErrorContainer = Color(0xFFF4A5A5),
    outline = Color(0xFF333333),
    outlineVariant = Color(0xFF222222),
    inverseSurface = Color(0xFFF5F5F5),
    inverseOnSurface = Color(0xFF0A0A0A),
    inversePrimary = Color(0xFF6B5BFF),
    scrim = Color(0xFF000000),
    surfaceTint = Color(0xFF9B8AFF)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF4F46E5),
    primaryContainer = Color(0xFFEEEEFF),
    secondary = Color(0xFF6366F1),
    secondaryContainer = Color(0xFFEDE9FE),
    tertiary = Color(0xFF8B5CF6),
    tertiaryContainer = Color(0xFFF5F3FF),
    background = Color(0xFFFAFAFA),
    surface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFFF0F0F5),
    surfaceContainerLow = Color(0xFFFFFFFF),
    surfaceContainer = Color(0xFFFAFAFA),
    surfaceContainerHigh = Color(0xFFF5F5FA),
    surfaceContainerHighest = Color(0xFFF0F0F5),
    error = Color(0xFFDC2626),
    errorContainer = Color(0xFFFEF2F2),
    onPrimary = Color(0xFFFFFFFF),
    onPrimaryContainer = Color(0xFF1E1A4A),
    onSecondary = Color(0xFFFFFFFF),
    onSecondaryContainer = Color(0xFF1E1A4A),
    onTertiary = Color(0xFFFFFFFF),
    onTertiaryContainer = Color(0xFF1E1A4A),
    onBackground = Color(0xFF0A0A0A),
    onSurface = Color(0xFF1A1A2E),
    onSurfaceVariant = Color(0xFF5A5A7A),
    onError = Color(0xFFFFFFFF),
    onErrorContainer = Color(0xFFDC2626),
    outline = Color(0xFFD0D0E0),
    outlineVariant = Color(0xFFE8E8F0),
    inverseSurface = Color(0xFF1A1A2E),
    inverseOnSurface = Color(0xFFFAFAFA),
    inversePrimary = Color(0xFFA5B4FC),
    scrim = Color(0xFF000000),
    surfaceTint = Color(0xFF4F46E5)
)

@Composable
fun Theme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

// Helper para obtener colores en código no-Compose
fun Context.getThemeColor(@ColorRes resId: Int): Int =
    ContextCompat.getColor(this, resId)

val Shapes = Shapes(
    extraSmall = androidx.compose.foundation.shape.RoundedCornerShape(4.dp),
    small = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
    medium = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
    large = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
    extraLarge = androidx.compose.foundation.shape.RoundedCornerShape(24.dp)
)

object ThemeColors {
    // Dark
    const val DARK_BACKGROUND = 0xFF0A0A0A
    const val DARK_SURFACE = 0xFF121212
    const val DARK_SURFACE_VARIANT = 0xFF1A1A1A
    const val DARK_PRIMARY = 0xFF9B8AFF
    const val DARK_PRIMARY_CONTAINER = 0xFF2A2440
    const val DARK_ON_SURFACE = 0xFFE8E8E8
    const val DARK_ON_SURFACE_VARIANT = 0xFFA0A0A0
    const val DARK_OUTLINE = 0xFF333333

    // Light
    const val LIGHT_BACKGROUND = 0xFFFAFAFA
    const val LIGHT_SURFACE = 0xFFFFFFFF
    const val LIGHT_SURFACE_VARIANT = 0xFFF0F0F5
    const val LIGHT_PRIMARY = 0xFF4F46E5
    const val LIGHT_PRIMARY_CONTAINER = 0xFFEEEEFF
    const val LIGHT_ON_SURFACE = 0xFF1A1A2E
    const val LIGHT_ON_SURFACE_VARIANT = 0xFF5A5A7A
    const val LIGHT_OUTLINE = 0xFFD0D0E0
}