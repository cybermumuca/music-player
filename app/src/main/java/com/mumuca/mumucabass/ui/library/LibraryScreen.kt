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
import androidx.navigation.NavHostController
import com.mumuca.mumucabass.ui.library.components.CollectionCard
import com.mumuca.mumucabass.ui.navigation.Screen


data class Collection(val title: String, val type: String, val author: String, val cover: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(navController: NavHostController) {
    val collections = listOf<Collection>(
        Collection("Mídia Local", "Playlist", "keyboardistmumuca", "https://picsum.photos/200/300"),
        Collection("Adeus Tokyo Part I", "Álbum", "Japa", "https://picsum.photos/200/300"),
        Collection("Adeus Tokyo Part II", "Álbum", "Japa", "https://picsum.photos/200/300"),
        Collection("FNB>BKB", "Single", "Japa", "https://picsum.photos/200/300")
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
                items(collections.size) { index ->
                    CollectionCard(
                        title = collections[index].title,
                        type = collections[index].type,
                        author = collections[index].author,
                        cover = collections[index].cover,
                        onClick = {
                            navController.navigate("${Screen.CollectionScreen}/${collections[index].title}")
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
