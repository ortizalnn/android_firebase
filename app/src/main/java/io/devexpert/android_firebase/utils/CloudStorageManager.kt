package io.devexpert.android_firebase.utils

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class CloudStorageManager {
    private val storage = FirebaseStorage.getInstance()

    suspend fun upload(uri: Uri, fileName: String): String {
        val ref = storage.reference.child("images/$fileName")
        ref.putFile(uri).await()
        return ref.downloadUrl.await().toString()
    }
}
