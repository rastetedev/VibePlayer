package com.raulastete.vibeplayer.di

import com.raulastete.vibeplayer.presentation.features.search.SearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val searchModule = module {
    viewModelOf(::SearchViewModel)
}