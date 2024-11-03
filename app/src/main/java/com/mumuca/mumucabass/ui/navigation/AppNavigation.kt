package com.mumuca.mumucabass.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.mumuca.mumucabass.ui.album.AlbumScreen
import com.mumuca.mumucabass.ui.library.LibraryScreen

@Composable
fun MumucaBassApp() {
    val navController = rememberNavController()
    val startScreen = Screen.LibraryScreen
    AppNavigation(navController, startScreen)
}

@Composable
fun AppNavigation(navController: NavHostController, startDestination: Screen) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable<Screen.LibraryScreen> {
            LibraryScreen(onAlbumClick = { albumId ->
                navController.navigate(Screen.AlbumScreen(albumId))
            })
        }
        composable<Screen.AlbumScreen> { backStackEntry ->
            val data = backStackEntry.toRoute<Screen.AlbumScreen>()
            AlbumScreen(albumId = data.albumId)
        }
    }
}