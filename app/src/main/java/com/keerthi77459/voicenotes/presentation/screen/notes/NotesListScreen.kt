package com.keerthi77459.voicenotes.presentation.screen.notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keerthi77459.voicenotes.domain.model.Note
import com.keerthi77459.voicenotes.presentation.viewmodel.AuthViewModel
import com.keerthi77459.voicenotes.presentation.viewmodel.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListScreen(
        viewModel: NotesViewModel,
        authViewModel: AuthViewModel,
        onAddNoteClick: () -> Unit,
        onLogout: () -> Unit
) {
    val notes by viewModel.notes.collectAsStateWithLifecycle()
    var showMenu by remember { mutableStateOf(false) }

    Scaffold(
            topBar = {
                TopAppBar(
                        title = { Text("Voice Notes") },
                        actions = {
                            IconButton(onClick = { showMenu = !showMenu }) {
                                Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                            }
                            DropdownMenu(
                                    expanded = showMenu,
                                    onDismissRequest = { showMenu = false }
                            ) {
                                DropdownMenuItem(
                                        text = { Text("Logout") },
                                        onClick = {
                                            showMenu = false
                                            onLogout()
                                        }
                                )
                            }
                        }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = onAddNoteClick) {
                    Icon(Icons.Default.Add, contentDescription = "Add Note")
                }
            }
    ) { paddingValues ->
        LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.padding(paddingValues).fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(notes) { note ->
                NoteItem(note = note, onClick = { /* TODO: Open Detail/Edit */})
            }
        }
    }
}

@Composable
fun NoteItem(note: Note, onClick: () -> Unit) {
    // Generate a random color or use fixed one
    val colors =
            listOf(
                    Color(0xFFF8BBD0), // Pink
                    Color(0xFFE1BEE7), // Purple
                    Color(0xFFC5CAE9), // Indigo
                    Color(0xFFB3E5FC), // Blue
                    Color(0xFFB2DFDB), // Teal
                    Color(0xFFC8E6C9), // Green
                    Color(0xFFDCEDC8), // Light Green
                    Color(0xFFFFF9C4), // Yellow
                    Color(0xFFFFE0B2), // Orange
                    Color(0xFFFFCCBC) // Deep Orange
            )
    val randomColor = remember(note.id) { colors.random() }

    Card(
            colors = CardDefaults.cardColors(containerColor = randomColor),
            modifier = Modifier.fillMaxWidth().height(180.dp).clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                    text = note.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                    text = note.content,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
            )
        }
    }
}
