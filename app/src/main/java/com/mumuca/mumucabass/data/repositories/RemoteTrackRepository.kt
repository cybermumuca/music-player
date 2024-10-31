package com.mumuca.mumucabass.data.repositories

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
    private val tracks = emptyList<Track>()

    override fun getTracksByAlbumId(albumId: Int): Flow<List<Track>> = flow {
        emit(if (albumId == 1) contentResolver.getTrackData() else tracks)
    }.flowOn(Dispatchers.IO)
}