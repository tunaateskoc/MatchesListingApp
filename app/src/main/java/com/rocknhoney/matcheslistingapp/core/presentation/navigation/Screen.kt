package com.rocknhoney.matcheslistingapp.core.presentation.navigation

/**
 * Sealed class representing different screens in the application.
 * Each screen is represented by a unique route.
 *
 * @param route The route associated with the screen.
 */
sealed class Screen(val route: String) {
    data object MatchesScreen : Screen("matches_screen")

    data object DetailScreen : Screen("detail_screen")

    fun withArgs(vararg args: String?): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}