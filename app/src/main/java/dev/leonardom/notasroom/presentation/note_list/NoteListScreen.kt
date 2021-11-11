package dev.leonardom.notasroom.presentation.note_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import dev.leonardom.notasroom.domain.model.Note
import dev.leonardom.notasroom.domain.model.NoteColor
import dev.leonardom.notasroom.presentation.note_list.components.NoteDesign
import dev.leonardom.notasroom.presentation.ui.noteAppColors
import dev.leonardom.notasroom.presentation.utils.getNoteColorFromIndex

@Composable
fun NoteListScreen(
    noteList: List<Note>
) {

    val noteColorList = listOf(
        NoteColor(0, MaterialTheme.noteAppColors.appBgColor),
        NoteColor(1, MaterialTheme.noteAppColors.red001),
        NoteColor(2, MaterialTheme.noteAppColors.pink002),
        NoteColor(3, MaterialTheme.noteAppColors.orange003),
        NoteColor(4, MaterialTheme.noteAppColors.orange004),
        NoteColor(5, MaterialTheme.noteAppColors.yellow005),
        NoteColor(6, MaterialTheme.noteAppColors.green006),
        NoteColor(7, MaterialTheme.noteAppColors.green007),
        NoteColor(8, MaterialTheme.noteAppColors.green008),
        NoteColor(9, MaterialTheme.noteAppColors.blue009),
        NoteColor(10, MaterialTheme.noteAppColors.blue010),
        NoteColor(11, MaterialTheme.noteAppColors.purple011),
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.noteAppColors.secondaryColor,
                onClick = {  },
            ) {
                Image(
                    imageVector = Icons.Default.Add,
                    contentDescription = "New Note Button",
                    colorFilter = ColorFilter.tint(MaterialTheme.noteAppColors.onSecondaryColor),
                )
            }
        },
        topBar = {}
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.noteAppColors.appBgColor)
        ){

            items(noteList) { note ->
                NoteDesign(
                    note = note,
                    noteColor = getNoteColorFromIndex(noteColorList, note.color),
                    modifyNote = {  }
                )
            }
        }
    }

}