package com.keerthi77459.voicenotes.di

import com.keerthi77459.voicenotes.data.repository.AuthRepositoryImpl
import com.keerthi77459.voicenotes.data.repository.NoteRepositoryImpl
import com.keerthi77459.voicenotes.domain.repository.AuthRepository
import com.keerthi77459.voicenotes.domain.repository.NoteRepository
import com.keerthi77459.voicenotes.domain.usecase.*

object AppModule {
    private val noteRepository: NoteRepository by lazy { NoteRepositoryImpl() }
    private val authRepository: AuthRepository by lazy { AuthRepositoryImpl() }

    val getNotesUseCase: GetNotesUseCase by lazy { GetNotesUseCase(noteRepository) }
    val addNoteUseCase: AddNoteUseCase by lazy { AddNoteUseCase(noteRepository) }
    val deleteNoteUseCase: DeleteNoteUseCase by lazy { DeleteNoteUseCase(noteRepository) }
    val updateNoteUseCase: UpdateNoteUseCase by lazy {
        UpdateNoteUseCase(noteRepository)
    } // Need to create this usecase file or add it to NoteUseCases.kt if missing. I forgot to add
    // UpdateNoteUseCase in previous step.

    val getCurrentUserUseCase: GetCurrentUserUseCase by lazy {
        GetCurrentUserUseCase(authRepository)
    }
    val loginUseCase: LoginUseCase by lazy { LoginUseCase(authRepository) }
    val signUpUseCase: SignUpUseCase by lazy { SignUpUseCase(authRepository) }
    val logoutUseCase: LogoutUseCase by lazy { LogoutUseCase(authRepository) }
    val getAuthStateUseCase: GetAuthStateUseCase by lazy { GetAuthStateUseCase(authRepository) }
}
