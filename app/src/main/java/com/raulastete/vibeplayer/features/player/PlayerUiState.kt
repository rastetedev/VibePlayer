package com.raulastete.vibeplayer.features.player

import com.raulastete.vibeplayer.features.home.components.TrackItemUi

data class PlayerUiState(
    val currentTrack: TrackItemUi? = null,
    val isPlaying: Boolean = false
)