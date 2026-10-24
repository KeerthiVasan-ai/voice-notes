package com.keerthi77459.voicenotes.domain.repository

import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: FirebaseUser?
    fun getAuthState(): Flow<FirebaseUser?>
    suspend fun login(email: String, pass: String): Result<Unit>
    suspend fun signUp(email: String, pass: String): Result<Unit>
    suspend fun logout()
    suspend fun sendEmailVerification()
}
