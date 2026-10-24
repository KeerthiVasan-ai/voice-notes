package com.keerthi77459.voicenotes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keerthi77459.voicenotes.domain.model.Note
import com.keerthi77459.voicenotes.domain.usecase.AddNoteUseCase
import com.keerthi77459.voicenotes.domain.usecase.DeleteNoteUseCase
import com.keerthi77459.voicenotes.domain.usecase.GetNotesUseCase
import com.keerthi77459.voicenotes.domain.usecase.UpdateNoteUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotesViewModel(
        getNotesUseCase: GetNotesUseCase,
        private val addNoteUseCase: AddNoteUseCase,
        private val deleteNoteUseCase: DeleteNoteUseCase,
        private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel() {

    val notes: StateFlow<List<Note>> =
            getNotesUseCase()
                    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addNote(note: Note) {
        viewModelScope.launch { addNoteUseCase(note) }
    }

    fun deleteNote(noteId: String) {
        viewModelScope.launch { deleteNoteUseCase(noteId) }
    }

    fun updateNote(noteId: String, note: Note) {
        viewModelScope.launch { updateNoteUseCase(noteId, note) }
    }
}
