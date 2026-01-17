package com.raulastete.vibeplayer.presentation.features.permission

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raulastete.vibeplayer.domain.FlagsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PermissionViewmodel(
    private val flagsRepository: FlagsRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(PermissionUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadPermissionRequestedFlag()
    }

    private fun loadPermissionRequestedFlag() {
        viewModelScope.launch {
            flagsRepository.getPermissionRequestedFlag()
                .collectLatest { value ->
                    _uiState.update {
                        it.copy(permissionRequestedAtLeastOnce = value)
                    }
                }
        }
    }

    fun dismissRationalPermissionDialog() {
        _uiState.update {
            it.copy(showRationalPermissionDialog = false)
        }
    }

    fun showRationalPermissionDialog() {
        _uiState.update {
            it.copy(showRationalPermissionDialog = true)
        }
    }

    fun onPermissionRequested() {
        viewModelScope.launch {
            dismissRationalPermissionDialog()
            flagsRepository.setPermissionRequestedFlag()
        }
    }
}