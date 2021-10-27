package dev.leonardom.notasroom.presentation.note_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.leonardom.notasroom.R
import dev.leonardom.notasroom.domain.model.Note
import dev.leonardom.notasroom.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel
@Inject
constructor(
    private val noteRepository: NoteRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private var _selectedColor = MutableStateFlow(R.color.app_bg_color)
    val selectedColor: StateFlow<Int> = _selectedColor

    private var _note = MutableStateFlow<Note?>(null)
    val note: StateFlow<Note?> = _note

    private var _noteHasBeenModified = MutableStateFlow(false)
    val noteHasBeenModified: StateFlow<Boolean> = _noteHasBeenModified

    init {
        savedStateHandle.get<String>("noteId")?.let { noteId ->
            getNoteById(noteId)
        }
    }

    private fun getNoteById(noteId: String) {
        noteRepository.getNoteById(noteId).onEach { note ->
            _note.value = note
        }.launchIn(viewModelScope)
    }

    fun updateNoteColor(newSelectedColor: Int) {
        _selectedColor.value = newSelectedColor
    }

    fun saveNoteChanges(title: String, content: String) {
        if(_note.value == null) {
            saveNewNote(title, content)
        } else {
            updateNote(title, content)
        }
    }

    private fun updateNote(title: String, content: String) {
        val noteModified = _note.value!!.copy(
            title = title,
            content = content,
            color = _selectedColor.value,
            updated = System.currentTimeMillis()
        )

        noteRepository.updateNote(noteModified).onEach {
            _noteHasBeenModified.value = true
        }.launchIn(viewModelScope)
    }

    private fun saveNewNote(title: String, content: String) {

        if(title.isEmpty() && content.isEmpty()) {
            _noteHasBeenModified.value = true
            return
        }

        val note = Note(
            title = title,
            content = content,
            color = _selectedColor.value
        )

        noteRepository.insertNote(note).onEach {
            _noteHasBeenModified.value = true
        }.launchIn(viewModelScope)
    }

    fun deleteNote() {
        _note.value?.id?.let { noteId ->
            noteRepository.deleteNote(noteId).onEach {
                _noteHasBeenModified.value = true
            }.launchIn(viewModelScope)
        }
    }

}






















