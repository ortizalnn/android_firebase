package io.devexpert.android_firebase.utils

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

class GoogleSignInManager(context: Context) {
    private val credentialManager = CredentialManager.create(context)

    suspend fun getIdToken(): String? {
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setServerClientId(WEB_CLIENT_ID)
                    .setFilterByAuthorizedAccounts(false)
                    .setAutoSelectEnabled(true)
                    .build()
            )
            .build()

        val result = credentialManager.getCredential(context, request)
        return (result.credential as? GoogleIdTokenCredential)?.idToken
    }

    companion object {
        // Web client (client_type 3) del proyecto android-firebase-ddb09
        private const val WEB_CLIENT_ID =
            "66834808692-cd2kj2aj1bikkrvckgnijm0g47sqh9j9.apps.googleusercontent.com"
    }
}
