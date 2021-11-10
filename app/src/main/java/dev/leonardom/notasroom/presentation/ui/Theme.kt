package dev.leonardom.notasroom.presentation.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White

private val DarkColorPalette = darkColors(
    primary = AppBgColorDark,
    primaryVariant = White,
    secondary = SecondaryColorDark,
    secondaryVariant = Black,
)

private val LightColorPalette = lightColors(
    primary = AppBgColor,
    primaryVariant = Black,
    secondary = SecondaryColor,
    secondaryVariant = White,
)


@Composable
fun AppNotasRoomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}