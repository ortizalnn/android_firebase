package io.devexpert.android_firebase.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

class AuthManager {
    private val auth = FirebaseAuth.getInstance()

    val currentUser get() = auth.currentUser
    val isLoggedIn get() = currentUser != null

    suspend fun signInAnonymously() = auth.signInAnonymously().await()

    suspend fun signUp(email: String, password: String) =
        auth.createUserWithEmailAndPassword(email, password).await()

    suspend fun signIn(email: String, password: String) =
        auth.signInWithEmailAndPassword(email, password).await()

    suspend fun signInWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).await()
    }

    suspend fun sendPasswordReset(email: String) = auth.sendPasswordResetEmail(email).await()

    fun signOut() = auth.signOut()
}
