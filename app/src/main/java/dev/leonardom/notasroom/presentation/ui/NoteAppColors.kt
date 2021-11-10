package dev.leonardom.notasroom.presentation.ui

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

data class NoteAppColors(
    val material: Colors,
    val appBgColor: Color,
    val secondaryColor: Color,
    val onSecondaryColor: Color,
    val noteTitleColor: Color,
    val noteContentColor: Color,
    val red001: Color,
    val pink002: Color,
    val orange003: Color,
    val orange004: Color,
    val yellow005: Color,
    val green006: Color,
    val green007: Color,
    val green008: Color,
    val blue009: Color,
    val blue010: Color,
    val purple011: Color
){
    val primary: Color get() = material.primary
    val primaryVariant: Color get() = material.primaryVariant
    val secondary: Color get() = material.secondary
    val secondaryVariant: Color get() = material.secondaryVariant
    val background: Color get() = material.background
    val surface: Color get() = material.surface
    val error: Color get() = material.error
    val onPrimary: Color get() = material.onPrimary
    val onSecondary: Color get() = material.onSecondary
    val onBackground: Color get() = material.onBackground
    val onSurface: Color get() = material.onSurface
    val onError: Color get() = material.onError
    val isLight: Boolean get() = material.isLight
}
















