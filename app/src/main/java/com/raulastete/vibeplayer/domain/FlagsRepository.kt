package com.raulastete.vibeplayer.domain

import kotlinx.coroutines.flow.Flow

interface FlagsRepository {

    suspend fun setPermissionRequestedFlag()

    fun getPermissionRequestedFlag(): Flow<Boolean>
}