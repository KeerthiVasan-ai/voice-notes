package com.keerthi77459.voicenotes.domain.repository

import com.keerthi77459.voicenotes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>
    suspend fun addNote(note: Note)
    suspend fun deleteNote(noteId: String)
    suspend fun updateNote(noteId: String, note: Note)
}
