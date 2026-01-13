package com.raulastete.vibeplayer.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raulastete.vibeplayer.domain.MusicTrack
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Entity
data class MusicTrackEntity(
    @PrimaryKey
    val id: String,
    val albumArtUri: String,
    val title: String,
    val artistName: String,
    val playTimeMillis: Long
)

@OptIn(ExperimentalUuidApi::class)
fun MusicTrack.toEntity() =
    MusicTrackEntity(
        id = Uuid.random().toHexString(),
        albumArtUri = albumArtUri,
        title = title,
        artistName = artistName,
        playTimeMillis = playTimeMillis
    )

fun MusicTrackEntity.toDomain() =
    MusicTrack(
        id = id,
        albumArtUri = albumArtUri,
        title = title,
        artistName = artistName,
        playTimeMillis = playTimeMillis
    )