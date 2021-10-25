package dev.leonardom.notasroom.data.data_source

import dev.leonardom.notasroom.R
import dev.leonardom.notasroom.domain.model.Note

fun getNoteList() = listOf(
    Note(
        title = "Título 1",
        content = "Contenido de nota numero 1"
    ),
    Note(
        title = "Título 2",
        content = "Contenido de nota numero 2.\n\n1\n2\n3",
        color = R.color.blue009
    ),
    Note(
        title = "Título 3",
        content = "Contenido de nota 3, el cual resulta muy largo y produce un cambio en las dimensiones del contenedor.\n\nLorem Ipsum is simply dummy text of the printing and typesetting industry.\n\nLorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
        color = R.color.blue010
    ),
    Note(
        title = "Título 4",
        content = "Contenido de nota numero 4",
        color = R.color.purple011
    ),
    Note(
        title = "Título 5",
        content = "Contenido de nota numero 5.\n\n1\n2\n3\n4",
        color = R.color.yellow005
    ),
)