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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.devexpert.android_firebase.utils.AnalyticsManager
import io.devexpert.android_firebase.utils.AuthManager
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val error = remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()
    val auth = remember { AuthManager() }
    val context = LocalContext.current

    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Crear cuenta", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(email.value, { email.value = it }, label = { Text("Correo") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        OutlinedTextField(password.value, { password.value = it }, label = { Text("Contraseña") }, modifier = Modifier.fillMaxWidth(), singleLine = true, visualTransformation = PasswordVisualTransformation())
        error.value?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        Button(onClick = {
            scope.launch {
                runCatching {
                    auth.signUp(email.value, password.value)
                    AnalyticsManager(context).logSignUp("email")
                    navController.popBackStack()
                }.onFailure { error.value = it.message }
            }
        }, modifier = Modifier.fillMaxWidth()) { Text("Registrarse") }
    }
}
