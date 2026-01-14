package com.raulastete.vibeplayer.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.raulastete.vibeplayer.domain.FlagsRepository
import kotlinx.coroutines.flow.map

class DefaultFlagsRepository(private val context: Context) : FlagsRepository {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_flags")

    private val permissionRequestedKey = booleanPreferencesKey("permission_requested_at_least_once")

    override suspend fun setPermissionRequestedFlag() {
        context.dataStore.edit { settings ->
            settings[permissionRequestedKey] = true
        }
    }

    override fun getPermissionRequestedFlag() = context.dataStore.data
        .map { preferences ->
            preferences[permissionRequestedKey] ?: false
        }
}