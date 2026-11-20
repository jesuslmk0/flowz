package com.darkdeveloper.kawaiianimes.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.darkdeveloper.kawaiianimes.presentation.anime.AnimeDetailScreen
import com.darkdeveloper.kawaiianimes.presentation.anime.EpisodeListScreen
import com.darkdeveloper.kawaiianimes.presentation.home.HomeScreen
import com.darkdeveloper.kawaiianimes.presentation.player.PlayerScreen
import com.darkdeveloper.kawaiianimes.presentation.profile.ProfileScreen
import com.darkdeveloper.kawaiianimes.presentation.search.SearchScreen
import com.darkdeveloper.kawaiianimes.presentation.settings.SettingsScreen
import javax.inject.Inject

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("search") { SearchScreen() }
        composable(
            route = "anime/{animeId}",
            arguments = listOf(androidx.navigation.navArgument("animeId") { type = androidx.navigation.NavType.StringType })
        ) { backStackEntry ->
            val animeId = backStackEntry.getString()!!
            AnimeDetailScreen(animeId = animeId)
        }
        composable(
            route = "anime/{animeId}/episodes",
            arguments = listOf(androidx.navigation.navArgument("animeId") { type = androidx.navigation.NavType.StringType })
        ) { backStackEntry ->
            val animeId = backStackEntry.getString()!!
            EpisodeListScreen(animeId = animeId)
        }
        composable(
            route = "player/{animeId}/{episode}",
            arguments = listOf(
                androidx.navigation.navArgument("animeId") { type = androidx.navigation.NavType.StringType },
                androidx.navigation.navArgument("episode") { type = androidx.navigation.NavType.IntType }
            )
        ) { backStackEntry ->
            val animeId = backStackEntry.getString()!!
            val episode = backStackEntry.getInt()!!
            PlayerScreen(animeId = animeId, episodeNumber = episode)
        }
        composable("profile") { ProfileScreen() }
        composable("settings") { SettingsScreen() }
    }
}