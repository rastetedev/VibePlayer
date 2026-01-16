package com.raulastete.vibeplayer.features.player

import com.raulastete.vibeplayer.ui.components.MusicTrackItemUi
import com.raulastete.vibeplayer.features.player.components.MusicTrackRepeatingMode

data class PlayerUiState(
    val currentTrack: MusicTrackItemUi? = null,
    val isPlaying: Boolean = false,
    val isInShuffleMode: Boolean = false,
    val repeatingMode: MusicTrackRepeatingMode = MusicTrackRepeatingMode.REPEAT_ALL
)