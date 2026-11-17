package io.devexpert.android_firebase

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

class AndroidFirebaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        FirebaseAnalytics.getInstance(this).setAnalyticsCollectionEnabled(true)
        FirebaseRemoteConfig.getInstance().apply {
            setConfigSettingsAsync(FirebaseRemoteConfigSettings.Builder().build())
            setDefaultsAsync(R.xml.remote_config_defaults)
            fetchAndActivate()
        }
    }
}
