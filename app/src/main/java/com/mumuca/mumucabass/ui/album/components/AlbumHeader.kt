package com.mumuca.mumucabass.ui.album.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mumuca.mumucabass.domain.entity.Album

@Composable
fun AlbumHeader(album: Album) {
    Row(modifier = Modifier.padding(bottom = 16.dp)) {
        AsyncImage(
            model = album.cover,
            contentDescription = null,
            modifier = Modifier.size(128.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = album.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = album.author, fontSize = 16.sp, color = Color.Gray)
        }
    }
}