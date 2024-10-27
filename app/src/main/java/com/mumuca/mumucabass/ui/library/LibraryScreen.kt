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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mumuca.mumucabass.data.local.models.Album
import com.mumuca.mumucabass.ui.library.components.AlbumCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(onAlbumClick: (Int) -> Unit) {
    val albums = listOf<Album>(
        Album(1, "Mídia Local", "Playlist", "keyboardistmumuca", "https://picsum.photos/200/300"),
        Album(2, "Adeus Tokyo Part I", "Álbum", "Japa", "https://picsum.photos/200/300"),
        Album(3, "Adeus Tokyo Part II", "Álbum", "Japa", "https://picsum.photos/200/300"),
        Album(4, "FNB>BKB", "Single", "Japa", "https://picsum.photos/200/300")
    )

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
