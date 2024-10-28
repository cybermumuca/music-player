package com.mumuca.mumucabass.data.repositories

import android.net.Uri
import com.mumuca.mumucabass.data.ContentResolverHelper
import com.mumuca.mumucabass.domain.entity.Track
import com.mumuca.mumucabass.domain.repository.TrackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteTrackRepository @Inject constructor(
    private val contentResolver: ContentResolverHelper
): TrackRepository() {
    private val tracks = listOf(
        Track(Uri.EMPTY, "Track 1", 1L, "Artist 1", "data1", 210, "Title 1"),
        Track(Uri.EMPTY, "Track 2", 2L, "Artist 2", "data2", 180, "Title 2"),
        Track(Uri.EMPTY, "Track 3", 3L, "Artist 3", "data3", 240, "Title 3"),
        Track(Uri.EMPTY, "Track 4", 4L, "Artist 4", "data4", 200, "Title 4"),
        Track(Uri.EMPTY, "Track 5", 5L, "Artist 5", "data5", 220, "Title 5")
    )

    override fun getTracksByAlbumId(albumId: Int): Flow<List<Track>> = flow {
        emit(if (albumId == 1) contentResolver.getTrackData() else tracks)
    }.flowOn(Dispatchers.IO)
}