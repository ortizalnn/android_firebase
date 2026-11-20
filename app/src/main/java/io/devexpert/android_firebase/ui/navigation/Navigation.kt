package io.devexpert.android_firebase.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.devexpert.android_firebase.ui.screens.HomeScreen
import io.devexpert.android_firebase.ui.screens.auth.ForgotPasswordScreen
import io.devexpert.android_firebase.ui.screens.auth.LoginScreen
import io.devexpert.android_firebase.ui.screens.auth.SignUpScreen
import io.devexpert.android_firebase.ui.screens.db.ContactsScreen
import io.devexpert.android_firebase.ui.screens.db.NotesScreen
import io.devexpert.android_firebase.ui.screens.storage.CloudStorageScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) { HomeScreen(navController) }
        composable(Routes.LOGIN) { LoginScreen(navController) }
        composable(Routes.SIGNUP) { SignUpScreen(navController) }
        composable(Routes.FORGOT) { ForgotPasswordScreen(navController) }
        composable(Routes.NOTES) { NotesScreen(navController) }
        composable(Routes.CONTACTS) { ContactsScreen(navController) }
        composable(Routes.STORAGE) { CloudStorageScreen(navController) }
    }
}
