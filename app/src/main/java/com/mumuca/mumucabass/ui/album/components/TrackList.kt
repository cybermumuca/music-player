package com.mumuca.mumucabass.ui.album.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.mumuca.mumucabass.domain.entity.Track

@Composable
fun TrackList(tracks: List<Track>) {
    LazyColumn {
        items(tracks.size) { trackIndex ->
            TrackCard(track = tracks[trackIndex])
        }
    }
}