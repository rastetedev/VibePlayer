package com.raulastete.vibeplayer.di

import androidx.room.Room
import com.raulastete.vibeplayer.core.AndroidDefaultDispatchers
import com.raulastete.vibeplayer.core.AppDispatchers
import com.raulastete.vibeplayer.data.DefaultMusicTrackRepository
import com.raulastete.vibeplayer.data.DefaultFlagsRepository
import com.raulastete.vibeplayer.data.local.database.MusicTrackDao
import com.raulastete.vibeplayer.data.local.database.VibePlayerDatabase
import com.raulastete.vibeplayer.domain.MusicTrackRepository
import com.raulastete.vibeplayer.domain.FlagsRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import kotlin.jvm.java


val appModule = module {
    singleOf(::DefaultMusicTrackRepository).bind<MusicTrackRepository>()
    single<VibePlayerDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            VibePlayerDatabase::class.java,
            "vibe_player.db"
        )
            .build()
    }
    single<MusicTrackDao> {
        get<VibePlayerDatabase>().musicTrackDao()
    }
    singleOf(::AndroidDefaultDispatchers).bind<AppDispatchers>()

    singleOf(::DefaultFlagsRepository).bind<FlagsRepository>()
}