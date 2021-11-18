package dev.leonardom.notasroom.presentation.utils

import androidx.compose.ui.graphics.Color
import dev.leonardom.notasroom.domain.model.NoteColor

fun getNoteColorFromIndex(colorList: List<NoteColor>, colorIndex: Int): Color {
    return colorList[colorIndex].color
}