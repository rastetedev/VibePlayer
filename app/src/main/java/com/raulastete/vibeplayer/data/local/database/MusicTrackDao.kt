package com.raulastete.vibeplayer.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicTrackDao {

    @Query("SELECT * FROM MusicTrackEntity")
    fun getAll() : Flow<List<MusicTrackEntity>>

    @Insert
    suspend fun saveAll(musicTrackEntities: List<MusicTrackEntity>)
}