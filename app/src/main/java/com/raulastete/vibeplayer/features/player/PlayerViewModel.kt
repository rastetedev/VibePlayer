package com.raulastete.vibeplayer.features.player

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.raulastete.vibeplayer.features.home.components.MusicTrackItemUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val TRACK_ITEM_ID_ARG = "trackItemId"

class PlayerViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val trackItemId: String = checkNotNull(savedStateHandle[TRACK_ITEM_ID_ARG])

    private var _uiState = MutableStateFlow(PlayerUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                currentTrack = MusicTrackItemUi(
                    id = trackItemId,
                    artwork = Uri.EMPTY,
                    title = "Titulo de prueba $trackItemId",
                    artistName = "Artista de prueba",
                    musicLengthFormatted = "4:45"
                )
            )
        }
    }
}