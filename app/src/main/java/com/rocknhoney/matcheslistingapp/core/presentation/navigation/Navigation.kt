package com.rocknhoney.matcheslistingapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rocknhoney.matcheslistingapp.features.detail.DetailScreen
import com.rocknhoney.matcheslistingapp.features.matches.MatchesScreen

/**
 * Composable function responsible for handling navigation within the application.
 * It sets up the navigation graph using Jetpack Navigation Compose library.
 */
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MatchesScreen.route) {
        composable(route = Screen.MatchesScreen.route) {
            MatchesScreen(navController = navController)
        }
        composable(
            route = Screen.DetailScreen.withArgs("{match}"),
            arguments = listOf(navArgument("match") {
                type = NavType.StringType
            })
        ) {
            DetailScreen()
        }
    }
}