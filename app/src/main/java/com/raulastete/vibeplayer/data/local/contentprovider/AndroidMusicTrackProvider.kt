package com.raulastete.vibeplayer.data.local.contentprovider

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import com.raulastete.vibeplayer.data.local.MusicTrackProvider
import com.raulastete.vibeplayer.domain.MusicTrack
import androidx.core.net.toUri

class AndroidMusicTrackProvider(
    private val context: Context
) : MusicTrackProvider {

    override suspend fun getAudioMediaLocalFiles(): List<MusicTrack> {

        val trackList = mutableListOf<MusicTrack>()

        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.ALBUM_ID
        )

        val cursor = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            "${MediaStore.Audio.Media.IS_MUSIC} != 0",
            null,
            null
        )

        cursor?.use { c ->
            val titleColumn = c.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistColumn = c.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val albumIdColumn = c.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)
            val durationColumn = c.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            val id = c.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)

            while (c.moveToNext()) {
                val title = c.getString(titleColumn)
                val artist = c.getString(artistColumn)
                val albumId = c.getLong(albumIdColumn)
                val duration = c.getLong(durationColumn)

                val albumArtUri = ContentUris.withAppendedId(
                    "content://media/external/audio/albumart".toUri(),
                    albumId
                )

                trackList.add(
                    MusicTrack(
                        id = id.toString(),
                        albumArtUri = albumArtUri.toString(),
                        title = title,
                        artistName = artist,
                        playTimeMillis = duration
                    )
                )
            }
        }

        return trackList
    }
}