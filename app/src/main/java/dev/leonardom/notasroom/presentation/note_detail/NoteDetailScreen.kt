package dev.leonardom.notasroom.presentation.note_detail

import android.text.format.DateUtils
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.leonardom.notasroom.domain.model.Note
import dev.leonardom.notasroom.domain.model.NoteColor
import dev.leonardom.notasroom.presentation.ui.noteAppColors
import dev.leonardom.notasroom.presentation.utils.getNoteColorFromIndex
import java.text.SimpleDateFormat
import java.util.*
import dev.leonardom.notasroom.R

@Composable
fun NoteDetailScreen(
    note: Note?,
    selectedNoteColorIndex: Int,
    saveNoteChanges: (String,String) -> Unit,
    deleteNote: () -> Unit,
    onColorSelectedIndex: (Int) -> Unit
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

    val systemUiController = rememberSystemUiController()

    LaunchedEffect(key1 = note) {
        note?.let {
            onColorSelectedIndex(it.color)
        }
    }

    LaunchedEffect(key1 = selectedNoteColorIndex){
        systemUiController.setSystemBarsColor(
            color = getNoteColorFromIndex(noteColorList, selectedNoteColorIndex)
        )
    }

    var noteTitle by remember(note?.title) { mutableStateOf(note?.title ?: "") }
    var noteContent by remember(note?.content) { mutableStateOf(note?.content ?: "") }

    var isTitleHintDisplayed by remember(note?.title)  { mutableStateOf(noteTitle.isBlank()) }
    var isContentHintDisplayed by remember(note?.content) { mutableStateOf(noteContent.isBlank()) }

    BackHandler(enabled = true) {
        saveNoteChanges(
            noteTitle,
            noteContent
        )
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = getNoteColorFromIndex(noteColorList, selectedNoteColorIndex)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        saveNoteChanges(
                            noteTitle,
                            noteContent
                        )
                    },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Button",
                colorFilter = ColorFilter.tint(color = MaterialTheme.noteAppColors.onSecondaryColor)
            )

            if(note != null){
                Image(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { deleteNote() },
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Note Button",
                    colorFilter = ColorFilter.tint(color = MaterialTheme.noteAppColors.onSecondaryColor)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f)
        ){
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ){
                val (titleSpace, contentSpace) = createRefs()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .constrainAs(titleSpace) {
                            top.linkTo(parent.top)
                        }
                ){
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusChanged {
                                isTitleHintDisplayed = !it.isFocused && noteTitle.isBlank()
                            },
                        value = noteTitle,
                        onValueChange = { noteTitle = it },
                        textStyle = TextStyle(
                            color = MaterialTheme.colors.onSecondary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 22.sp
                        ),
                    )

                    if(isTitleHintDisplayed){
                        Text(
                            text = "Título",
                            style = TextStyle(
                                color = Color.DarkGray,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 22.sp
                            )
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 18.dp)
                        .constrainAs(contentSpace) {
                            top.linkTo(titleSpace.bottom)
                        },
                    contentAlignment = Alignment.TopStart
                ){
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusChanged {
                                isContentHintDisplayed = !it.isFocused && noteContent.isBlank()
                            },
                        value = noteContent,
                        onValueChange = { noteContent = it },
                        textStyle = TextStyle(
                            color = MaterialTheme.colors.onSecondary,
                            fontSize = 16.sp
                        )
                    )

                    if(isContentHintDisplayed){
                        Text(
                            text = "Nota",
                            style = TextStyle(
                                color = Color.DarkGray,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                .weight(0.1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .weight(0.1f)
                    .clickable { },
                painter = painterResource(id = R.drawable.ic_palette),
                contentDescription = "Change Note Color Button",
                colorFilter = ColorFilter.tint(color = MaterialTheme.noteAppColors.onSecondaryColor)
            )

            if(note != null){
                val updatedAt = Date(note.updated)

                val dateFormat: SimpleDateFormat = if(DateUtils.isToday(updatedAt.time)) {
                    SimpleDateFormat("hh:mm a", Locale.ROOT)
                } else {
                    SimpleDateFormat("MMM dd", Locale.ROOT)
                }

                Text(
                    modifier = Modifier.weight(0.8f),
                    text = "Editado ${dateFormat.format(updatedAt)}",
                    style = TextStyle(
                        color = MaterialTheme.noteAppColors.onSecondaryColor,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                )
            } else {
                Spacer(modifier = Modifier
                    .size(24.dp)
                    .weight(0.8f)
                )
            }

            Spacer(modifier = Modifier
                .size(24.dp)
                .weight(0.1f)
            )
        }
    }

}