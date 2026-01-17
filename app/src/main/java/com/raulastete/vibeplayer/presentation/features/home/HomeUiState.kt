package com.raulastete.vibeplayer.presentation.features.home

import com.raulastete.vibeplayer.presentation.ui.components.MusicTrackItemUi

 data class HomeUiState(
    val state: ContentState = ContentState.Empty
)

 sealed interface ContentState {
    data object Empty : ContentState
    data object Scanning : ContentState
    data class TrackList(val musicTrackItems: List<MusicTrackItemUi>) : ContentState
}