package com.keerthi77459.voicenotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.keerthi77459.voicenotes.presentation.navigation.NavGraph
import com.keerthi77459.voicenotes.presentation.navigation.Screen
import com.keerthi77459.voicenotes.presentation.theme.VoiceNotesTheme
import com.keerthi77459.voicenotes.presentation.viewmodel.AuthViewModel
import com.keerthi77459.voicenotes.presentation.viewmodel.NotesViewModel
import com.keerthi77459.voicenotes.presentation.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = ViewModelFactory()
        val authViewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]
        val notesViewModel = ViewModelProvider(this, factory)[NotesViewModel::class.java]

        setContent {
            VoiceNotesTheme {
                val navController = rememberNavController()
                val startDestination =
                        if (authViewModel.currentUser.value != null) {
                            Screen.Home.route
                        } else {
                            Screen.Login.route
                        }

                NavGraph(
                        navController = navController,
                        authViewModel = authViewModel,
                        notesViewModel = notesViewModel,
                        startDestination = startDestination
                )
            }
        }
    }
}
