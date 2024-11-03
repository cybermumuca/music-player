package com.mumuca.mumucabass.data.local.repository.helper
import android.media.MediaMetadataRetriever
import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.WorkerThread
import com.mumuca.mumucabass.domain.entity.Album
import com.mumuca.mumucabass.domain.entity.Track
import com.mumuca.mumucabass.domain.entity.TrackMetadata
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ContentResolverHelper @Inject constructor(@ApplicationContext private val context: Context) {
    private val projection = arrayOf(
        MediaStore.Audio.Media._ID,
        MediaStore.Audio.Media.TITLE,
        MediaStore.Audio.Media.ARTIST,
        MediaStore.Audio.Media.ALBUM,
        MediaStore.Audio.Media.ALBUM_ARTIST,
        MediaStore.Audio.Media.YEAR,
        MediaStore.Audio.Media.DURATION,
        MediaStore.Audio.Media.SIZE,
        MediaStore.Audio.Media.DATE_ADDED,
        MediaStore.Audio.Media.DATA,
        MediaStore.Audio.Media.COMPOSER,
        MediaStore.Audio.Media.MIME_TYPE,
        MediaStore.Audio.Media.IS_MUSIC,
        MediaStore.Audio.Media.DISC_NUMBER,
        MediaStore.Audio.Media.TRACK,
    )

    private val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0 AND ${MediaStore.Audio.Media.DURATION} > 0"
    private val sortOrder = "${MediaStore.Audio.AudioColumns.DISPLAY_NAME} ASC"

    @WorkerThread
    fun getTracks(): List<Track> {
        return context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
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
        val id = getLong(getColumnIndexOrThrow(MediaStore.Audio.Media._ID))
        val title = getString(getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE))
        val artist = getString(getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
        val album = getString(getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM))
        val albumArtist = getString(getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ARTIST))
        val duration = getLong(getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))
        val dataPath = getString(getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))

        val metadata = getTrackMetadata(dataPath)

        return Track(
            id,
            title,
            artist,
            album,
            albumArtist,
            duration,
            dataPath,
            null, // TODO: cover
            metadata,
            Track.Source.LOCAL
        )
    }

    private fun getTrackMetadata(dataPath: String): TrackMetadata {
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(dataPath)

        // TODO: add more metadata
        return TrackMetadata(
            genre = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE),
            year = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_YEAR)?.toIntOrNull(),
            diskNumber = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DISC_NUMBER)?.toIntOrNull(),
            trackNumber = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_CD_TRACK_NUMBER)?.toIntOrNull(),
            trackTotal = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_NUM_TRACKS)?.toIntOrNull(),
            composer = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_COMPOSER),
            copyright = null, // retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_COPYRIGHT),
            publisher = null, // retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_PUBLISHER),
            encoder = null, // retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ENCODER),
            language = null, // retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_LANGUAGE),
            comment = null, // retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_COMMENT),
            lyrics = null, // retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_LYRIC),
            bitrate = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE)?.toIntOrNull()
        ).also {
            retriever.release()
        }
    }
}