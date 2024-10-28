package com.mumuca.mumucabass.ui.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.mumuca.mumucabass.domain.entity.Album
import com.mumuca.mumucabass.domain.entity.Track
import com.mumuca.mumucabass.domain.repository.AlbumRepository
import com.mumuca.mumucabass.domain.repository.TrackRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val albumRepository: AlbumRepository,
    private val trackRepository: TrackRepository
) : ViewModel() {
    private val _album = MutableStateFlow<Album?>(null)
    val album: StateFlow<Album?> get() = _album

    private val _tracks = MutableStateFlow<List<Track>>(emptyList())
    val tracks: StateFlow<List<Track>> get() = _tracks

    fun fetchAlbumWithTracks(albumId: Int) {
        viewModelScope.launch {
            albumRepository.getAlbumById(albumId).collect { fetchedAlbum ->
                _album.value = fetchedAlbum

                fetchedAlbum?.let {
                    trackRepository.getTracksByAlbumId(it.id).collect { fetchedTracks ->
                        _tracks.value = fetchedTracks
                    }
                }
            }
        }
    }

}