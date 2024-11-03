package com.mumuca.mumucabass.data.remote.repository

import com.mumuca.mumucabass.domain.entity.Album
import com.mumuca.mumucabass.domain.repository.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteAlbumRepository @Inject constructor(): AlbumRepository() {
    private val albums = listOf<Album>(
        Album(1, "Mídia Local", "Playlist", "keyboardistmumuca", "https://picsum.photos/200/300"),
        Album(2, "Adeus Tokyo Part I", "Álbum", "Japa", "https://picsum.photos/200/300"),
        Album(3, "Adeus Tokyo Part II", "Álbum", "Japa", "https://picsum.photos/200/300"),
        Album(4, "FNB>BKB", "Single", "Japa", "https://picsum.photos/200/300")
    )

    override fun getAlbums(): Flow<List<Album>> = flow {
        emit(albums)
    }.flowOn(Dispatchers.IO)

    override fun getAlbumById(id: Int): Flow<Album?> = flow {
        emit(albums.first { it.id == id })
    }.flowOn(Dispatchers.IO)
}