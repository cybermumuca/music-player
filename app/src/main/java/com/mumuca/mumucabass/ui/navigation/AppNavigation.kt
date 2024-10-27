package com.mumuca.mumucabass.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mumuca.mumucabass.ui.collection.CollectionScreen
import com.mumuca.mumucabass.ui.library.LibraryScreen

object Screen {
    const val LibraryScreen = "libraryScreen"
    const val CollectionScreen = "collectionScreen"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.LibraryScreen) {
        composable(Screen.LibraryScreen) {
            LibraryScreen(navController)
        }
        composable("${Screen.CollectionScreen}/{albumName}") { backStackEntry ->
            val albumName = backStackEntry.arguments?.getString("albumName") ?: "Default Album"
            val artistName = backStackEntry.arguments?.getString("artistName") ?: "Default Artist"
            val collectionCover = backStackEntry.arguments?.getString("collectionCover") ?: "https://picsum.photos/200/300"
            CollectionScreen(albumName = albumName, artistName = artistName, collectionCover = collectionCover)
        }
    }
}