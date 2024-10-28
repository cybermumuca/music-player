package com.mumuca.mumucabass.domain.repository

import com.mumuca.mumucabass.domain.entity.Album
import kotlinx.coroutines.flow.Flow

abstract class AlbumRepository {
    abstract fun getAlbums(): Flow<List<Album>>
    abstract fun getAlbumById(id: Int): Flow<Album?>
}