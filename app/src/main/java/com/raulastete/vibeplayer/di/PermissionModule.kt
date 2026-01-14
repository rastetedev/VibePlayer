package com.raulastete.vibeplayer.di

import com.raulastete.vibeplayer.features.permission.PermissionViewmodel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val permissionModule = module {
    viewModelOf(::PermissionViewmodel)
}