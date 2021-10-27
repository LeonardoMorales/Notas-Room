package dev.leonardom.notasroom.domain.repositories

import dev.leonardom.notasroom.data.cache.note.NoteDao
import dev.leonardom.notasroom.data.cache.note.toNote
import dev.leonardom.notasroom.data.cache.note.toNoteEntity
import dev.leonardom.notasroom.data.data_source.getNoteList
import dev.leonardom.notasroom.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class NoteRepository(
    private val noteDao: NoteDao
){

    fun insertNote(note: Note): Flow<Boolean> = flow {
        noteDao.insertNote(note.toNoteEntity())

        emit(true)
    }.catch { e ->
        e.printStackTrace()
    }

    fun getNoteById(noteId: String): Flow<Note?> = flow {
        val note = noteDao.getNoteById(noteId)?.toNote()

        emit(note)
    }.catch { e ->
        e.printStackTrace()
    }

    fun getNotes(
        query: String
    ): Flow<List<Note>> = flow {

        val cachedNoteList = noteDao.getNotes(query).map{ it.toNote() }

        if(cachedNoteList.isNullOrEmpty()) {
            throw Exception("UNABLE TO RETRIEVE NOTE LIST")
        }

        emit(cachedNoteList)

    }.catch { e ->
        e.printStackTrace()
    }

}