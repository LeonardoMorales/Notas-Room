package dev.leonardom.notasroom.presentation.note_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.leonardom.notasroom.R
import dev.leonardom.notasroom.domain.model.NoteColor

@Composable
fun ColorItemDesign(
    noteColor: NoteColor,
    displayIcon: Boolean,
    onColorSelectedIndex: (Int) -> Unit,
) {

    Box(
        modifier = Modifier.size(60.dp).padding(8.dp),
        contentAlignment = Alignment.Center
    ){
        Box(
            modifier = Modifier
                .size(48.dp)
                .border(1.dp, MaterialTheme.colors.onSecondary, CircleShape)
                .background(noteColor.color, CircleShape)
                .clickable { onColorSelectedIndex(noteColor.index) }
        )

        if(displayIcon){
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_color_reset),
                contentDescription = "Note Color Reset",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onSecondary)
            )
        }
    }

}