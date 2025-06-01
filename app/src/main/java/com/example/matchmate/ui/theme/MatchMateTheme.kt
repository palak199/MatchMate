package com.example.matchmate.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Dark Theme
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFCD780),
    secondary = Color(0xFF03DAC6),
    background = Color(0xFF111010),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.Gray,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

// Light Theme with modern colors
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFCD780),
    secondary = Color(0xFFBA68C8),
    tertiary = Color(0xFF4DD0E1),
    background = Color(0xFFFCF9F1),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.Gray,
    onSecondary = Color.Black,
    onSurface = Color(0xFF333333),
    onBackground = Color(0xFF444444)
)

val LightGradient = Brush.verticalGradient(
    colors = listOf(

        Color(0xFFFCF9F1),
        Color(0xFFFCF9F1),
        Color(0xFFFFFFFF))
)

val DarkGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF111010),
        Color(0xFF343232))
)

// Modern Typography
private val MatchMateTypography = Typography(
    titleLarge = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.SemiBold
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    labelLarge = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),
    headlineSmall = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
)

@Composable
fun MatchMateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colors,
        typography = MatchMateTypography,
        content = content
    )
}
