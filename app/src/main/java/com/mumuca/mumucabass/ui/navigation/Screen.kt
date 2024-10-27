package com.mumuca.mumucabass.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    object LibraryScreen: Screen()
    @Serializable
    data class AlbumScreen(val albumId: Int): Screen()
}