package com.raulastete.vibeplayer.features.permission

data class PermissionUiState(
    val showRationalPermissionDialog: Boolean = false,
    val permissionRequestedAtLeastOnce: Boolean = false
)