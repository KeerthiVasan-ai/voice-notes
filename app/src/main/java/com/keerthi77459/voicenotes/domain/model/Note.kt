package com.keerthi77459.voicenotes.domain.model

import com.google.firebase.Timestamp

data class Note(
    val title: String = "",
    val content: String = "",
    val timestamp: Timestamp? = null,
    val id: String = "" // Added ID to track document ID
)
