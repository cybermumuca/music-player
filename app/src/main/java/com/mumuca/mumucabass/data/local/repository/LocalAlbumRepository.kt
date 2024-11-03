package com.mumuca.mumucabass.data.local.repository

import com.mumuca.mumucabass.data.local.repository.helper.ContentResolverHelper
import com.mumuca.mumucabass.domain.entity.Album
import com.mumuca.mumucabass.domain.repository.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocalAlbumRepository @Inject constructor(
    private val contentResolverHelper: ContentResolverHelper
): AlbumRepository() {
    private val albums: List<Album> = emptyList()

    override fun getAlbums(): Flow<List<Album>> = flow {
        emit(albums)
    }.flowOn(Dispatchers.IO)

    override fun getAlbumById(id: Int): Flow<Album?> = flow {
        emit(albums.first { it.id == id })
    }.flowOn(Dispatchers.IO)
}