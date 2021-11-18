package dev.leonardom.notasroom.presentation.note_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.leonardom.notasroom.domain.model.Note
import dev.leonardom.notasroom.presentation.ui.noteAppColors

@Composable
fun NoteDesign(
    modifier: Modifier = Modifier,
    note: Note,
    noteColor: Color,
    modifyNote: (String?) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .background(color = noteColor, RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = if(note.color == 0) {
                    MaterialTheme.noteAppColors.onSecondaryColor} else MaterialTheme.noteAppColors.appBgColor,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(8.dp)
            .clickable { modifyNote(note.id) }
    ) {
        if(note.title.isNotEmpty()){
            Text(
                text = note.title,
                style = TextStyle(
                    color = MaterialTheme.noteAppColors.noteTitleColor,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if(note.content.isNotEmpty()){
            Text(
                text = note.content,
                style = TextStyle(
                    color = MaterialTheme.noteAppColors.noteContentColor,
                    fontSize = 14.sp
                ),
                maxLines = 10
            )
        }
    }
}