package com.keerthi77459.voicenotes.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.keerthi77459.voicenotes.domain.model.Note
import com.keerthi77459.voicenotes.domain.repository.NoteRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class NoteRepositoryImpl : NoteRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun getNotes(): Flow<List<Note>> = callbackFlow {
        val user = auth.currentUser
        if (user == null) {
            close(Exception("User not logged in"))
            return@callbackFlow
        }

        val collection = firestore.collection("notes")
            .document(user.uid)
            .collection("myNotes")
            .orderBy("timestamp", Query.Direction.DESCENDING)

        val listener = collection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }

            val notes = snapshot?.documents?.mapNotNull { doc ->
                doc.toObject(Note::class.java)?.copy(id = doc.id)
            } ?: emptyList()
            
            trySend(notes)
        }

        awaitClose { listener.remove() }
    }

    override suspend fun addNote(note: Note) {
        val user = auth.currentUser ?: throw Exception("User not logged in")
        firestore.collection("notes")
            .document(user.uid)
            .collection("myNotes")
            .add(note)
            .await()
    }

    override suspend fun deleteNote(noteId: String) {
        val user = auth.currentUser ?: throw Exception("User not logged in")
        firestore.collection("notes")
            .document(user.uid)
            .collection("myNotes")
            .document(noteId)
            .delete()
            .await()
    }

    override suspend fun updateNote(noteId: String, note: Note) {
        val user = auth.currentUser ?: throw Exception("User not logged in")
        firestore.collection("notes")
            .document(user.uid)
            .collection("myNotes")
            .document(noteId)
            .set(note)
            .await()
    }
}
