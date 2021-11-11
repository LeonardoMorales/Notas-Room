package dev.leonardom.notasroom.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.leonardom.notasroom.presentation.note_list.NoteListScreen
import dev.leonardom.notasroom.presentation.note_list.NoteListViewModel
import dev.leonardom.notasroom.presentation.ui.AppNotasRoomTheme
import dev.leonardom.notasroom.presentation.ui.RED50
import dev.leonardom.notasroom.presentation.ui.RED600
import dev.leonardom.notasroom.presentation.ui.noteAppColors

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNotasRoomTheme {

                val viewModel: NoteListViewModel = hiltViewModel()
                val noteList = viewModel.noteList.collectAsState()

                NoteListScreen(noteList = noteList.value)

            }
        }
    }
}