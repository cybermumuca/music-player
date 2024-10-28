package com.mumuca.mumucabass.ui.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mumuca.mumucabass.domain.entity.Album
import com.mumuca.mumucabass.domain.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val albumRepository: AlbumRepository
) : ViewModel() {
    private val _albums = MutableStateFlow<List<Album>>(emptyList())
    val albums: StateFlow<List<Album>> = _albums

    fun fetchAlbums() {
        viewModelScope.launch {
            albumRepository.getAlbums().collect { fetchedAlbums ->
                _albums.value = fetchedAlbums
            }
        }
    }
}