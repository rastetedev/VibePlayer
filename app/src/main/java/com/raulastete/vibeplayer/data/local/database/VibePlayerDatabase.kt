package com.raulastete.vibeplayer.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MusicTrackEntity::class], version = 1)
abstract class VibePlayerDatabase : RoomDatabase() {

    abstract fun musicTrackDao() : MusicTrackDao
}