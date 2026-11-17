package io.devexpert.android_firebase.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.devexpert.android_firebase.utils.AuthManager
import kotlinx.coroutines.launch

@Composable
fun ForgotPasswordScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val message = remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Recuperar contraseña", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(email.value, { email.value = it }, label = { Text("Correo") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        message.value?.let { Text(it) }
        Button(onClick = {
            scope.launch {
                runCatching {
                    AuthManager().sendPasswordReset(email.value)
                    message.value = "Correo enviado"
                }.onFailure { message.value = it.message }
            }
        }, modifier = Modifier.fillMaxWidth()) { Text("Enviar") }
    }
}
