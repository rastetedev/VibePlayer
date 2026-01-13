package com.raulastete.vibeplayer.features.player

import com.raulastete.vibeplayer.features.home.components.MusicTrackItemUi

data class PlayerUiState(
    val currentTrack: MusicTrackItemUi? = null,
    val isPlaying: Boolean = false
)