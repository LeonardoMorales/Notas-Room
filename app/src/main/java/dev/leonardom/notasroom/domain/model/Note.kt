package dev.leonardom.notasroom.domain.model

import dev.leonardom.notasroom.R
import java.util.*

data class Note(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val content: String,
    val color: Int = 0,
    val created: Long = System.currentTimeMillis(),
    val updated: Long = System.currentTimeMillis()
)
