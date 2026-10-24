package com.keerthi77459.voicenotes.presentation.screen.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.keerthi77459.voicenotes.presentation.viewmodel.AuthViewModel

@Composable
fun SignUpScreen(
        viewModel: AuthViewModel,
        onSignUpSuccess: () -> Unit,
        onNavigateToLogin: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Create Account", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                    onClick = {
                        if (email.isNotBlank() &&
                                        password.length >= 6 &&
                                        password == confirmPassword
                        ) {
                            isLoading = true
                            viewModel.signUp(
                                    email,
                                    password,
                                    onSuccess = {
                                        isLoading = false
                                        Toast.makeText(
                                                        context,
                                                        "Account Created. Please Login.",
                                                        Toast.LENGTH_SHORT
                                                )
                                                .show()
                                        onSignUpSuccess()
                                    },
                                    onError = { error ->
                                        isLoading = false
                                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                                    }
                            )
                        } else {
                            if (password != confirmPassword) {
                                Toast.makeText(
                                                context,
                                                "Passwords do not match",
                                                Toast.LENGTH_SHORT
                                        )
                                        .show()
                            } else {
                                Toast.makeText(context, "Invalid input", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
            ) { Text("Sign Up") }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onNavigateToLogin) { Text("Already have an account? Login") }
        }
    }
}
