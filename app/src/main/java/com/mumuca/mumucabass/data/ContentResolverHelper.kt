package com.mumuca.mumucabass.data

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.WorkerThread
import com.mumuca.mumucabass.domain.entity.Track
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ContentResolverHelper @Inject constructor(@ApplicationContext private val context: Context) {
    private val projection = arrayOf(
        MediaStore.Audio.AudioColumns.DISPLAY_NAME,
        MediaStore.Audio.AudioColumns._ID,
        MediaStore.Audio.AudioColumns.ARTIST,
        MediaStore.Audio.AudioColumns.DATA,
        MediaStore.Audio.AudioColumns.DURATION,
        MediaStore.Audio.AudioColumns.TITLE,
        MediaStore.Audio.AudioColumns.MIME_TYPE
    )

    private val selectionClause = "${MediaStore.Audio.AudioColumns.IS_MUSIC} = ?"
    private val selectionArgs = arrayOf("1")
    private val sortOrder = "${MediaStore.Audio.AudioColumns.DISPLAY_NAME} ASC"

    @WorkerThread
    fun getTrackData(): List<Track> {
        return context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selectionClause,
            selectionArgs,
            sortOrder
        )?.use { cursor ->

            if (cursor.count == 0) {
                Log.e("ContentResolverHelper", "getAudioData: Cursor is empty")
                return emptyList()
            }

            generateSequence { if (cursor.moveToNext()) cursor else null }
                .map { it.toTrack() }
                .toList()
        } ?: run {
            Log.e("ContentResolverHelper", "getAudioData: Cursor is null")
            emptyList()
        }
    }

    private fun Cursor.toTrack(): Track {
        val id = getLong(getColumnIndexOrThrow(MediaStore.Audio.AudioColumns._ID))
        val uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)

        return Track(
            uri,
            getString(getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DISPLAY_NAME)),
            id,
            getString(getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ARTIST)),
            getString(getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DATA)),
            getInt(getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DURATION)),
            getString(getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.TITLE))
        )
    }
}