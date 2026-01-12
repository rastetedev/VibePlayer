package com.raulastete.vibeplayer.features.home

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raulastete.vibeplayer.features.home.components.TrackItemUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

 class HomeViewModel : ViewModel() {

    private var _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()


    fun getLocalAudioFiles(context: Context) {

        _uiState.update {
            it.copy(
                state = ContentState.Scanning
            )
        }

        viewModelScope.launch(Dispatchers.IO) {
            delay(4_000)
            val trackList = mutableListOf<TrackItemUi>()

            val projection = arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM_ID // ¡IMPORTANTE!
            )

            // Consulta al MediaStore
            val cursor = context.contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                "${MediaStore.Audio.Media.IS_MUSIC} != 0", // Solo archivos de música
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
                        Uri.parse("content://media/external/audio/albumart"),
                        albumId
                    )

                    trackList.add(
                        TrackItemUi(
                            id = id.toString(),
                            artwork = albumArtUri,
                            title = title,
                            artistName = artist,
                            musicLengthFormatted = formatDuration(duration)
                        )
                    )
                }
            }

            withContext(Dispatchers.Main){
                _uiState.update {
                    it.copy(
                        state = ContentState.TrackList( trackItems = trackList.toList())
                    )
                }
            }
        }
    }

    fun formatDuration(duration: Long): String {
        val minutes = (duration / 1000) / 60
        val seconds = (duration / 1000) % 60
        return String.format("%d:%02d", minutes, seconds)
    }
}