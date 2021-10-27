package dev.leonardom.notasroom.presentation.note_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.leonardom.notasroom.domain.model.Note
import dev.leonardom.notasroom.domain.repositories.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel
@Inject
constructor(
    private val noteRepository: NoteRepository
): ViewModel() {

    private var _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList: StateFlow<List<Note>> = _noteList

    private var _searchQuery = MutableStateFlow("")

    init {
        getNotes()
    }

    fun getNotes() {
        noteRepository.getNotes(_searchQuery.value).onEach { noteList ->
            _noteList.value = noteList
        }.launchIn(viewModelScope)

    }

}