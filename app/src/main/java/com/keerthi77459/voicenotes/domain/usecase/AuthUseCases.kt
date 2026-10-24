package com.keerthi77459.voicenotes.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.keerthi77459.voicenotes.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentUserUseCase(private val repository: AuthRepository) {
    operator fun invoke(): FirebaseUser? = repository.currentUser
}

class LoginUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, pass: String) = repository.login(email, pass)
}

class SignUpUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, pass: String) = repository.signUp(email, pass)
}

class LogoutUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke() = repository.logout()
}

class GetAuthStateUseCase(private val repository: AuthRepository) {
    operator fun invoke(): Flow<FirebaseUser?> = repository.getAuthState()
}
