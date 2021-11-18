package dev.leonardom.notasroom.presentation.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White

private val LightColorPalette = NoteAppColors(
    material = lightColors(),
    appBgColor = AppBgColor,
    secondaryColor = SecondaryColor,
    onSecondaryColor = OnSecondaryColor,
    noteTitleColor = NoteTitleColor,
    noteContentColor = NoteContentColor,
    red001 = RED50,
    pink002 = PINK50,
    orange003 = ORANGE50,
    orange004 = ORANGE100,
    yellow005 = YELLOW50,
    green006 = GREEN50,
    green007 = GREEN100,
    green008 = GREEN200,
    blue009 = BLUE50,
    blue010 = BLUE100,
    purple011 = PURPLE50
)

private val DarkColorPalette = NoteAppColors(
    material = darkColors(),
    appBgColor = AppBgColorDark,
    secondaryColor = SecondaryColorDark,
    onSecondaryColor = OnSecondaryColorDark,
    noteTitleColor = NoteTitleColorDark,
    noteContentColor = NoteContentColorDark,
    red001 = RED600,
    pink002 = PINK600,
    orange003 = ORANGE600,
    orange004 = ORANGE700,
    yellow005 = YELLOW600,
    green006 = GREEN600,
    green007 = GREEN700,
    green008 = GREEN800,
    blue009 = BLUE600,
    blue010 = BLUE700,
    purple011 = PURPLE600
)

private val LocalColors = staticCompositionLocalOf { LightColorPalette }

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

    CompositionLocalProvider(LocalColors provides colors) {
        MaterialTheme(
            colors = colors.material,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

val MaterialTheme.noteAppColors: NoteAppColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current










