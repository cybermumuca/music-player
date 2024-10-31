package com.mumuca.mumucabass.domain.entity

data class TrackMetadata(
    val genre: String?,
    val year: Int?,
    val diskNumber: Int?,
    val trackNumber: Int?,
    val trackTotal: Int?,
    val composer: String?,
    val copyright: String?,
    val publisher: String?,
    val encoder: String?,
    val language: String?,
    val comment: String?,
    val lyrics: String?,
    val bitrate: Int?
)
