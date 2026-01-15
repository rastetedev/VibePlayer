package com.raulastete.vibeplayer.data

import com.raulastete.vibeplayer.core.AppDispatchers
import com.raulastete.vibeplayer.data.local.MusicTrackProvider
import com.raulastete.vibeplayer.data.local.database.MusicTrackDao
import com.raulastete.vibeplayer.data.local.database.toDomain
import com.raulastete.vibeplayer.data.local.database.toEntity
import com.raulastete.vibeplayer.domain.MusicTrack
import com.raulastete.vibeplayer.domain.MusicTrackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DefaultMusicTrackRepository(
    private val musicTrackDao: MusicTrackDao,
    private val musicTrackProvider: MusicTrackProvider,
    private val appDispatchers: AppDispatchers
) : MusicTrackRepository {

    override suspend fun syncMusicTracks() {

       withContext(appDispatchers.ioDispatcher){
           val isDbEmpty = musicTrackDao.getAll().first().isEmpty()

           if (isDbEmpty) {
               val localTracks = musicTrackProvider.getAudioMediaLocalFiles()
               if (localTracks.isNotEmpty()) {
                   musicTrackDao.saveAll(localTracks.map { it.toEntity() })
               }
           }
       }
    }

    override fun observeMusicTracks(): Flow<List<MusicTrack>> {
        return musicTrackDao.getAll().map { entities -> entities.map { it.toDomain() } }
    }

    override fun search(searchQuery: String): Flow<List<MusicTrack>> {
        return musicTrackDao.search(searchQuery).map { entities -> entities.map { it.toDomain() } }
    }
}