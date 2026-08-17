package com.themify.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val LightColors = lightColorScheme(
    primary          = Color(0xFF6750A4),
    onPrimary        = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFEADDFF),
    secondary        = Color(0xFF625B71),
    background       = Color(0xFFFFFBFE),
    surface          = Color(0xFFFFFBFE),
    onBackground     = Color(0xFF1C1B1F),
    onSurface        = Color(0xFF1C1B1F)
)

private val DarkColors = darkColorScheme(
    primary          = Color(0xFFD0BCFF),
    onPrimary        = Color(0xFF381E72),
    primaryContainer = Color(0xFF4F378B),
    secondary        = Color(0xFFCCC2DC),
    background       = Color(0xFF1C1B1F),
    surface          = Color(0xFF1C1B1F),
    onBackground     = Color(0xFFE6E1E5),
    onSurface        = Color(0xFFE6E1E5)
)

private val AppTypography = Typography(
    headlineLarge  = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold,     lineHeight = 40.sp),
    headlineMedium = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.SemiBold, lineHeight = 36.sp),
    titleLarge     = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.SemiBold, lineHeight = 28.sp),
    titleMedium    = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium,   lineHeight = 24.sp),
    bodyLarge      = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal,   lineHeight = 24.sp),
    bodyMedium     = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal,   lineHeight = 20.sp),
    labelSmall     = TextStyle(fontSize = 11.sp, fontWeight = FontWeight.Medium,   lineHeight = 16.sp)
)

@Composable
fun ThemifyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography  = AppTypography,
        content     = content
    )
}
