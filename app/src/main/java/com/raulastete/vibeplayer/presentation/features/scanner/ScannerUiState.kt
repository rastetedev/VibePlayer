package com.raulastete.vibeplayer.presentation.features.scanner

data class ScannerUiState(
    val isScanning: Boolean = false,
    val currentDurationFilter: TrackDuration = TrackDuration.SECONDS_30,
    val currentSizeFilter: TrackSize = TrackSize.KB_100
)