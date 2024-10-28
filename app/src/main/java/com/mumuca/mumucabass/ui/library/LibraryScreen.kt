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
    viewModel: LibraryViewModel = hiltViewModel()
) {
    val albums = viewModel.albums.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.fetchAlbums()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Biblioteca de Mídia", color = Color.White) }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(paddingValues)
            ) {
                items(albums.size) { index ->
                    AlbumCard(
                        title = albums[index].title,
                        type = albums[index].type,
                        author = albums[index].author,
                        cover = albums[index].cover,
                        onClick = {
                            onAlbumClick(albums[index].id)
                        }
                    )
                }
            }
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
