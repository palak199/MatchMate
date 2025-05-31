package com.example.matchmatee.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme: ColorScheme
    @Composable
    get() = darkColorScheme(
        primary = colorScheme.primary,
        secondary = colorScheme.secondary
    )

private val LightColorScheme: ColorScheme
    @Composable
    get() = lightColorScheme(
        primary = colorScheme.primary,
        secondary = colorScheme.secondary
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
