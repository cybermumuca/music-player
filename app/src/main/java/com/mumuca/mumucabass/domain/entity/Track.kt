package com.mumuca.mumucabass.domain.entity

data class Track(
    val id: Long,
    val title: String,
    val artist: String,
    val album: String,
    val albumArtist: String?,
    val duration: Long,
    val location: String?,
    val coverUri: String?,
    val metadata: TrackMetadata,
    val source: Source
) {
    enum class Source {
        API,
        LOCAL
    }
}