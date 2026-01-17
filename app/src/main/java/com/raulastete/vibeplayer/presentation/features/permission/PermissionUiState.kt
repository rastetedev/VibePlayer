package com.raulastete.vibeplayer.presentation.features.permission

data class PermissionUiState(
    val showRationalPermissionDialog: Boolean = false,
    val permissionRequestedAtLeastOnce: Boolean = false
)