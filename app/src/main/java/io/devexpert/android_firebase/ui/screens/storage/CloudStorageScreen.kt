package io.devexpert.android_firebase.ui.screens.storage

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.devexpert.android_firebase.utils.CloudStorageManager
import kotlinx.coroutines.launch

@Composable
fun CloudStorageScreen(navController: NavController) {
    val url = remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) scope.launch {
            runCatching { url.value = CloudStorageManager().upload(uri, "image_${System.currentTimeMillis()}.jpg") }
        }
    }

    Column(Modifier.fillMaxSize().padding(24.dp)) {
        Text("Cloud Storage", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = { launcher.launch("image/*") }) { Text("Subir imagen") }
        url.value?.let { Text("URL: $it") }
    }
}
