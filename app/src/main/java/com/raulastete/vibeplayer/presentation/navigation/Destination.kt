package com.raulastete.vibeplayer.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    object Permission : Destination

    @Serializable
    object Home : Destination

    @Serializable
    object Scanner : Destination

    @Serializable
    object Search : Destination

    @Serializable
    data class Player(val trackItemId: String) : Destination
}