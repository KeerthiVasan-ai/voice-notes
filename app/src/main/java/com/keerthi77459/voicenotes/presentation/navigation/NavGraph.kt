package com.keerthi77459.voicenotes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.keerthi77459.voicenotes.presentation.screen.auth.LoginScreen
import com.keerthi77459.voicenotes.presentation.screen.auth.SignUpScreen
import com.keerthi77459.voicenotes.presentation.screen.notes.AddNoteScreen
import com.keerthi77459.voicenotes.presentation.screen.notes.NotesListScreen
import com.keerthi77459.voicenotes.presentation.viewmodel.AuthViewModel
import com.keerthi77459.voicenotes.presentation.viewmodel.NotesViewModel

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object Home : Screen("home")
    object AddNote : Screen("add_note")
}

@Composable
fun NavGraph(
        navController: NavHostController,
        authViewModel: AuthViewModel,
        notesViewModel: NotesViewModel,
        startDestination: String = Screen.Login.route
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Login.route) {
            LoginScreen(
                    viewModel = authViewModel,
                    onLoginSuccess = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    },
                    onNavigateToSignUp = { navController.navigate(Screen.SignUp.route) }
            )
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(
                    viewModel = authViewModel,
                    onSignUpSuccess = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.SignUp.route) { inclusive = true }
                        }
                    },
                    onNavigateToLogin = { navController.popBackStack() }
            )
        }
        composable(Screen.Home.route) {
            NotesListScreen(
                    viewModel = notesViewModel,
                    authViewModel = authViewModel,
                    onAddNoteClick = { navController.navigate(Screen.AddNote.route) },
                    onLogout = {
                        authViewModel.logout()
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
            )
        }
        composable(Screen.AddNote.route) {
            AddNoteScreen(
                    viewModel = notesViewModel,
                    onNoteSaved = { navController.popBackStack() }
            )
        }
    }
}
