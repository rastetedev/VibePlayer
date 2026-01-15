package com.raulastete.vibeplayer.domain

import kotlinx.coroutines.flow.Flow

interface MusicTrackRepository {

    fun observeMusicTracks() : Flow<List<MusicTrack>>
    fun search(searchQuery: String): Flow<List<MusicTrack>>
    suspend fun syncMusicTracks()
}