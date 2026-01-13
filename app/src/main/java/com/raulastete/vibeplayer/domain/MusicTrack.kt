package com.raulastete.vibeplayer.domain

data class MusicTrack(
    val id: String,
    val albumArtUri: String,
    val title: String,
    val artistName: String,
    val playTimeMillis: Long
)