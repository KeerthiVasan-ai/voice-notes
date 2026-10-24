package com.keerthi77459.voicenotes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.keerthi77459.voicenotes.domain.usecase.GetAuthStateUseCase
import com.keerthi77459.voicenotes.domain.usecase.LoginUseCase
import com.keerthi77459.voicenotes.domain.usecase.LogoutUseCase
import com.keerthi77459.voicenotes.domain.usecase.SignUpUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthViewModel(
        private val loginUseCase: LoginUseCase,
        private val signUpUseCase: SignUpUseCase,
        private val logoutUseCase: LogoutUseCase,
        getAuthStateUseCase: GetAuthStateUseCase
) : ViewModel() {

    val currentUser: StateFlow<FirebaseUser?> =
            getAuthStateUseCase()
                    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun login(email: String, pass: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val result = loginUseCase(email, pass)
            if (result.isSuccess) {
                onSuccess()
            } else {
                onError(result.exceptionOrNull()?.message ?: "Login failed")
            }
        }
    }

    fun signUp(email: String, pass: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val result = signUpUseCase(email, pass)
            if (result.isSuccess) {
                onSuccess()
            } else {
                onError(result.exceptionOrNull()?.message ?: "Sign up failed")
            }
        }
    }

    fun logout() {
        viewModelScope.launch { logoutUseCase() }
    }
}
