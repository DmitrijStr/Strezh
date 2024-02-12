package com.fintech2023.compose

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fintech2023.compose.filmdetail.FilmDetailsScreen
import com.fintech2023.compose.home.HomeScreen


@Composable
fun FilmsApp() {
    val navController = rememberNavController()

    FilmsAppNavHost(navController = navController)
}

@Composable
fun FilmsAppNavHost(
    navController: NavHostController
) {
    val activity = (LocalContext.current as Activity)

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onFilmClick = {
                    navController.navigate(
                        Screen.FilmDetail.createRoute(
                            filmId = it.filmId
                        )
                    )
                }
            )
        }
        composable(
            route = Screen.FilmDetail.route,
            arguments = Screen.FilmDetail.navArg
        ) {
            FilmDetailsScreen(
                onBackClick = { navController.navigateUp() },

            )
        }
    }
}