package io.devexpert.android_firebase.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

abstract class Screen {
    @Composable
    abstract fun Content(navController: NavController)
}
