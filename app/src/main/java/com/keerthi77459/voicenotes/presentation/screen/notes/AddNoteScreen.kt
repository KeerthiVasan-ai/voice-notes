package com.keerthi77459.voicenotes.presentation.screen.notes

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import com.keerthi77459.voicenotes.domain.model.Note
import com.keerthi77459.voicenotes.presentation.viewmodel.NotesViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(viewModel: NotesViewModel, onNoteSaved: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Scaffold(
            topBar = {
                TopAppBar(
                        title = { Text("Add Note") },
                        navigationIcon = {
                            IconButton(onClick = onNoteSaved) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                            }
                        },
                        actions = {
                            IconButton(
                                    onClick = {
                                        if (title.isNotBlank() || content.isNotBlank()) {
                                            viewModel.addNote(
                                                    Note(
                                                            title = title,
                                                            content = content,
                                                            timestamp = Timestamp(Date())
                                                    )
                                            )
                                            onNoteSaved()
                                        }
                                    }
                            ) { Icon(Icons.Default.Check, contentDescription = "Save") }
                        }
                )
            }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).padding(16.dp).fillMaxSize()) {
            TextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Text("Title") },
                    textStyle = MaterialTheme.typography.headlineSmall,
                    colors =
                            TextFieldDefaults.colors(
                                    focusedContainerColor = MaterialTheme.colorScheme.background,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                                    focusedIndicatorColor =
                                            MaterialTheme.colorScheme.background, // Transparent
                                    unfocusedIndicatorColor =
                                            MaterialTheme.colorScheme.background // Transparent
                            ),
                    modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                    value = content,
                    onValueChange = { content = it },
                    placeholder = { Text("Note Content") },
                    textStyle = MaterialTheme.typography.bodyLarge,
                    colors =
                            TextFieldDefaults.colors(
                                    focusedContainerColor = MaterialTheme.colorScheme.background,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                                    focusedIndicatorColor = MaterialTheme.colorScheme.background,
                                    unfocusedIndicatorColor = MaterialTheme.colorScheme.background
                            ),
                    modifier = Modifier.fillMaxSize()
            )
        }
    }
}
