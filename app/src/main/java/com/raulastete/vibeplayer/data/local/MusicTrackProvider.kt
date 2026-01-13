package com.raulastete.vibeplayer.data.local

import com.raulastete.vibeplayer.domain.MusicTrack

interface MusicTrackProvider {

    suspend fun getAudioMediaLocalFiles() : List<MusicTrack>
}