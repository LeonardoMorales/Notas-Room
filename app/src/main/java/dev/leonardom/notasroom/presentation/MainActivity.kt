package dev.leonardom.notasroom.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.leonardom.notasroom.presentation.navigation.Destination
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

            val viewModel: NoteListViewModel = hiltViewModel()
            val isDarkMode = viewModel.darkMode.collectAsState()

            AppNotasRoomTheme(
                darkTheme = isDarkMode.value
            ) {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Destination.NoteList.route
                ){
                    addNoteList(navController)

                    addNoteDetail(navController)
                }
            }
        }
    }
}

fun NavGraphBuilder.addNoteList(
    navController: NavController
){
    composable(
        route = Destination.NoteList.route
    ){
        val viewModel: NoteListViewModel = hiltViewModel()

        val noteList = viewModel.noteList.collectAsState()
        val isLinearLayoutMode = viewModel.linearLayoutMode.collectAsState()

        LaunchedEffect(key1 = noteList){
            viewModel.getNotes()
        }

        NoteListScreen(
            noteList = noteList.value,
            isLinearLayoutMode = isLinearLayoutMode.value,
            toggleLayoutMode = viewModel::toggleLayoutMode,
            toggleDarkMode = viewModel::toggleDarkMode,
            onSearchQuery = viewModel::updateQuery,
            modifyNote = { noteId ->
                navController.navigate(Destination.NoteDetail.route)
            }
        )
    }
}


fun NavGraphBuilder.addNoteDetail(
    navController: NavController
){
    composable(
        route = Destination.NoteDetail.route
    ){

    }
}


