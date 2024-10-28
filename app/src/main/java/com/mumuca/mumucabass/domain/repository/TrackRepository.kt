package com.mumuca.mumucabass.domain.repository

import com.mumuca.mumucabass.domain.entity.Track
import kotlinx.coroutines.flow.Flow

abstract class TrackRepository {
    abstract fun getTracksByAlbumId(albumId: Int): Flow<List<Track>>
}