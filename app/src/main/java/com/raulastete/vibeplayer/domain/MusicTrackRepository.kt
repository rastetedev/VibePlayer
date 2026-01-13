package com.raulastete.vibeplayer.domain

import kotlinx.coroutines.flow.Flow

interface MusicTrackRepository {

    fun observeMusicTracks() : Flow<List<MusicTrack>>
    suspend fun syncMusicTracks()
}