package com.raulastete.vibeplayer.di

import com.raulastete.vibeplayer.data.local.MusicTrackProvider
import com.raulastete.vibeplayer.data.local.contentprovider.AndroidMusicTrackProvider
import com.raulastete.vibeplayer.features.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    single<MusicTrackProvider>{
        AndroidMusicTrackProvider(context = androidApplication())
    }
    viewModelOf(::HomeViewModel)
}