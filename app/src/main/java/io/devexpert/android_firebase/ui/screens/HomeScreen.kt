package io.devexpert.android_firebase.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.devexpert.android_firebase.R
import io.devexpert.android_firebase.ui.navigation.Routes
import io.devexpert.android_firebase.utils.AuthManager
import io.devexpert.android_firebase.utils.ConfigManager

@Composable
fun HomeScreen(navController: NavController) {
    val welcome = remember { mutableStateOf("Bienvenido a Firebase") }
    val user = AuthManager().currentUser

    LaunchedEffect(Unit) {
        runCatching { welcome.value = ConfigManager().getWelcomeMessage() }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_firebase),
            contentDescription = "Firebase",
            modifier = Modifier.size(96.dp)
        )
        Text(welcome.value, style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center)
        Text(
            user?.let { "Sesión: ${it.email ?: "anónima"} (${it.uid})" } ?: "Sin sesión iniciada",
            style = MaterialTheme.typography.bodyMedium
        )
        listOf(
            "Iniciar sesión" to Routes.LOGIN,
            "Registrarse" to Routes.SIGNUP,
            "Recuperar contraseña" to Routes.FORGOT,
            "Notas (Realtime DB)" to Routes.NOTES,
            "Contactos (Firestore)" to Routes.CONTACTS,
            "Almacenamiento" to Routes.STORAGE
        ).forEach { (label, route) ->
            Button(onClick = { navController.navigate(route) }, modifier = Modifier.fillMaxWidth()) {
                Text(label)
            }
        }
    }
}
