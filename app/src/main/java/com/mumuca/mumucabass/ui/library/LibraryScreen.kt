package com.mumuca.mumucabass.ui.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.mumuca.mumucabass.ui.library.components.AlbumCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    onAlbumClick: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Biblioteca de Mídia", color = Color.White) }
            )
        },
        content = {
            // TODO: WIP
            // TODO: WIP Favorite Tracks Card
            // TODO: WIP Local Tracks Card
            // TODO: WIP ALbums Card
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.background(Color.DarkGray)
            ) {
                Text("Navegação Inferior", color = Color.White)
            }
        }
    )
}
