package com.keerthi77459.voicenotes.domain.usecase

import com.keerthi77459.voicenotes.domain.model.Note
import com.keerthi77459.voicenotes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(private val repository: NoteRepository) {
    operator fun invoke(): Flow<List<Note>> = repository.getNotes()
}

class AddNoteUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) = repository.addNote(note)
}

class DeleteNoteUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(noteId: String) = repository.deleteNote(noteId)
}

class UpdateNoteUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(noteId: String, note: Note) = repository.updateNote(noteId, note)
}
