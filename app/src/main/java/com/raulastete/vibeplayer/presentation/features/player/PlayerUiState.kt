package com.raulastete.vibeplayer.presentation.features.player

import com.raulastete.vibeplayer.presentation.features.player.components.MusicTrackRepeatingMode
import com.raulastete.vibeplayer.presentation.ui.components.MusicTrackItemUi

data class PlayerUiState(
    val currentTrack: MusicTrackItemUi? = null,
    val isPlaying: Boolean = false,
    val isInShuffleMode: Boolean = false,
    val repeatingMode: MusicTrackRepeatingMode = MusicTrackRepeatingMode.REPEAT_ALL
)