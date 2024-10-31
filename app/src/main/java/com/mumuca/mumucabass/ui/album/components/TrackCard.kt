package com.mumuca.mumucabass.ui.album.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mumuca.mumucabass.domain.entity.Track
import com.mumuca.mumucabass.util.formatDuration

@Composable
fun TrackCard(track: Track) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = track.title, fontSize = 18.sp, fontWeight = FontWeight.Medium)
        Text(text = track.artist, fontSize = 14.sp, color = Color.Gray)
        Text(text = track.duration.formatDuration(), fontSize = 12.sp, color = Color.Gray)
    }
}