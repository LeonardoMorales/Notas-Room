package dev.leonardom.notasroom.presentation.navigation

import androidx.navigation.NamedNavArgument

sealed class Destination(
    val route: String,
    val arguments: List<NamedNavArgument>
){

    object NoteList : Destination("noteList", emptyList())

    object NoteDetail: Destination("noteDetail", emptyList())

}
