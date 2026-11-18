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
import io.devexpert.android_firebase.utils.RealtimeManager
import kotlinx.coroutines.launch

@Composable
fun NotesScreen(navController: NavController) {
    val note = remember { mutableStateOf("") }
    val notes = remember { mutableStateListOf<Pair<String, String>>() }
    val scope = rememberCoroutineScope()
    val manager = remember { RealtimeManager() }

    fun refresh() = scope.launch { runCatching { notes.apply { clear(); addAll(manager.read()) } } }

    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Notas - Realtime Database", style = MaterialTheme.typography.headlineMedium)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(note.value, { note.value = it }, label = { Text("Nueva nota") }, modifier = Modifier.weight(1f), singleLine = true)
            Button(onClick = {
                scope.launch {
                    runCatching {
                        manager.save(System.currentTimeMillis().toString(), note.value)
                        note.value = ""
                        refresh()
                    }
                }
            }) { Text("Guardar") }
        }
        LazyColumn(Modifier.fillMaxWidth()) {
            items(notes, key = { it.first }) { (key, value) ->
                Row(Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(value)
                    Button(onClick = { scope.launch { manager.delete(key); refresh() } }) { Text("Borrar") }
                }
            }
        }
    }
}
