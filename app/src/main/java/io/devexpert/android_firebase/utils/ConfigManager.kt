package io.devexpert.android_firebase.utils

import com.google.firebase.remoteconfig.FirebaseRemoteConfig

class ConfigManager {
    private val remoteConfig = FirebaseRemoteConfig.getInstance()

    suspend fun getWelcomeMessage(): String {
        remoteConfig.fetchAndActivate().await()
        return remoteConfig.getString("welcome_message")
    }
}
