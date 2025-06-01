package com.example.matchmatee.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC), // Example purple for dark theme
    secondary = Color(0xFF03DAC6), // Example teal
    background = Color(0xFF121212), // Dark background
    surface = Color(0xFF1E1E1E), // Dark surface (for cards, etc.)
)

private val LightColorScheme = lightColorScheme(
    secondary = Color(0xFFBA68C8),       // Light Purple
    primary = Color(0xFFE91E63),     // Pink
    tertiary = Color(0xFF4DD0E1),      // Light Cyan
    background = Color(0xFFFFFFFF),
    surface = Color(0xFF1E1E1E),       // Slightly lighter gray
    onPrimary = Color(0xFF000000),
    onSecondary = Color(0xFF000000),
    onSurface = Color(0xFFFFFFFF),
)


@Composable
fun MatchMateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        content = content
    )
}
