package io.devexpert.android_firebase.utils

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreManager {
    private val db = FirebaseFirestore.getInstance()

    suspend fun save(collection: String, data: Map<String, Any>) {
        db.collection(collection).add(data).await()
    }

    suspend fun read(collection: String): List<Map<String, Any>> =
        db.collection(collection).get().await().documents.map { it.data ?: emptyMap() }

    suspend fun delete(collection: String, documentId: String) {
        db.collection(collection).document(documentId).delete().await()
    }
}
