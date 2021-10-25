package dev.leonardom.notasroom.domain.repositories

import dev.leonardom.notasroom.data.data_source.getNoteList
import dev.leonardom.notasroom.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class NoteRepository {

    fun getNotes(): Flow<List<Note>> = flow {

        val cachedNoteList = getNoteList()

        emit(cachedNoteList)

    }.catch { e ->
        e.printStackTrace()
    }

}