package io.devexpert.android_firebase.utils

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class AnalyticsManager(context: Context) {
    private val analytics = FirebaseAnalytics.getInstance(context)

    fun logLogin(method: String) {
        analytics.logEvent(FirebaseAnalytics.Event.LOGIN) {
            param(FirebaseAnalytics.Param.METHOD, method)
        }
    }

    fun logSignUp(method: String) {
        analytics.logEvent(FirebaseAnalytics.Event.SIGN_UP) {
            param(FirebaseAnalytics.Param.METHOD, method)
        }
    }

    fun logScreen(name: String) {
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.SCREEN_NAME, name)
        }
    }

    fun logEvent(name: String, value: String) {
        analytics.logEvent(name, Bundle().apply { putString("value", value) })
    }
}
