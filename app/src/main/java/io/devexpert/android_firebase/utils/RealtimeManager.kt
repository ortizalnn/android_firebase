package io.devexpert.android_firebase.utils

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class RealtimeManager {
    private val db = FirebaseDatabase.getInstance()

    suspend fun save(key: String, value: String) {
        db.getReference("notes").child(key).setValue(value).await()
    }

    suspend fun read(): List<Pair<String, String>> {
        val snapshot: DataSnapshot = db.getReference("notes").get().await()
        return snapshot.children.mapNotNull { child ->
            child.key?.let { it to (child.value as? String ?: "") }
        }
    }

    suspend fun delete(key: String) {
        db.getReference("notes").child(key).removeValue().await()
    }
}
