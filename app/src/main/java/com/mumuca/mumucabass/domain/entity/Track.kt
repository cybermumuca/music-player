package com.mumuca.mumucabass.domain.entity

import android.net.Uri

data class Track(
    val uri: Uri,
    val displayName: String,
    val id: Long,
    val artist: String,
    val data: String,
    val duration: Int,
    val title: String,
)

