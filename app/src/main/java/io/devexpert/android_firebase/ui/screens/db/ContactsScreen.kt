package io.devexpert.android_firebase.ui.screens.db

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.devexpert.android_firebase.utils.FirestoreManager
import kotlinx.coroutines.launch

@Composable
fun ContactsScreen(navController: NavController) {
    val name = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val contacts = remember { mutableStateListOf<Map<String, Any>>() }
    val scope = rememberCoroutineScope()
    val manager = remember { FirestoreManager() }

    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Contactos - Cloud Firestore", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(name.value, { name.value = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        OutlinedTextField(phone.value, { phone.value = it }, label = { Text("Teléfono") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        Button(onClick = {
            scope.launch {
                runCatching {
                    manager.save("contacts", mapOf("name" to name.value, "phone" to phone.value))
                    name.value = ""; phone.value = ""
                    contacts.apply { clear(); addAll(manager.read("contacts")) }
                }
            }
        }, modifier = Modifier.fillMaxWidth()) { Text("Guardar contacto") }
        LazyColumn(Modifier.fillMaxWidth()) {
            items(contacts) { contact ->
                Row(Modifier.fillMaxWidth().padding(8.dp)) {
                    Text("${contact["name"]} - ${contact["phone"]}")
                }
            }
        }
    }
}
