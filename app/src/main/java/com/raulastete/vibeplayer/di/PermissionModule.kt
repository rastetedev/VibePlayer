package com.raulastete.vibeplayer.di

import com.raulastete.vibeplayer.presentation.features.permission.PermissionViewmodel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val permissionModule = module {
    viewModelOf(::PermissionViewmodel)
}