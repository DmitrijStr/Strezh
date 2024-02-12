package com.fintech2023.compose

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val navArg: List<NamedNavArgument> = emptyList()
) {
    data object Home : Screen("home")

    data object FilmDetail : Screen(
        route = "filmDetail/{filmId}",
        navArg = listOf(navArgument("filmId") {
            type = NavType.StringType
        })

    ) {
        fun createRoute(filmId: String) = "filmDetail/${filmId}"
    }
}
