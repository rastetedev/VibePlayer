package com.raulastete.vibeplayer.presentation.features.scanner

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ScannerViewModel : ViewModel() {

    private var _uiState: MutableStateFlow<ScannerUiState> = MutableStateFlow(ScannerUiState())
    val uiState: StateFlow<ScannerUiState> = _uiState.asStateFlow()

    fun scan() {
        _uiState.update {
            it.copy(
                isScanning = true
            )
        }
    }
}