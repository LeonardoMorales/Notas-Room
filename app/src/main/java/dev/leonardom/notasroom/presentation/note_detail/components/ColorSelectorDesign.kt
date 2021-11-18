package dev.leonardom.notasroom.presentation.note_detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.leonardom.notasroom.domain.model.NoteColor

@Composable
fun ColorSelectorDesign(
    noteColorList: List<NoteColor>,
    onColorSelectedIndex: (Int) -> Unit,
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(8.dp)
    ){
        itemsIndexed(
            items = noteColorList
        ){ index, noteColor ->
            if(index == 0){
                ColorItemDesign(noteColor,true, onColorSelectedIndex)
            } else {
                ColorItemDesign(noteColor,false, onColorSelectedIndex)
            }
        }
    }
}