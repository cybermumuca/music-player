package com.mumuca.mumucabass.data.local.repositories

import com.mumuca.mumucabass.data.local.ContentResolverHelper
import com.mumuca.mumucabass.data.local.models.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrackRepository @Inject constructor(
    private val contentResolver: ContentResolverHelper
) {
    suspend fun getTracks(): List<Track> = withContext(Dispatchers.IO) {
        contentResolver.getTrackData()
    }
}