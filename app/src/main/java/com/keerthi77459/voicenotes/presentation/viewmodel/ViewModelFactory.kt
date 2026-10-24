package com.keerthi77459.voicenotes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.keerthi77459.voicenotes.di.AppModule

class ViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(
                    AppModule.loginUseCase,
                    AppModule.signUpUseCase,
                    AppModule.logoutUseCase,
                    AppModule.getAuthStateUseCase
            ) as
                    T
        }
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(
                    AppModule.getNotesUseCase,
                    AppModule.addNoteUseCase,
                    AppModule.deleteNoteUseCase,
                    AppModule.updateNoteUseCase
            ) as
                    T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
