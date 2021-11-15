package dev.leonardom.notasroom.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument

sealed class Destination(
    val route: String,
    val arguments: List<NamedNavArgument>
){

    object NoteList : Destination("noteList", emptyList())

    object NoteDetail: Destination(
        route = "noteDetail",
        arguments = listOf(
            navArgument("noteId"){
                nullable = true
            }
        )
    )

}
