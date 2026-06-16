package org.vz.nxo.core.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

interface SettingsRepository {
    val isAiSortingEnabled: Flow<Boolean>
    suspend fun setAiSortingEnabled(enabled: Boolean)
    
    val geminiApiKey: Flow<String>
    suspend fun setGeminiApiKey(key: String)

    val topAppsCount: Flow<Int>
    suspend fun setTopAppsCount(count: Int)
}

class SettingsRepositoryImpl(private val context: Context) : SettingsRepository {
    private val AI_SORTING_KEY = booleanPreferencesKey("ai_sorting_enabled")
    private val GEMINI_API_KEY = stringPreferencesKey("gemini_api_key")
    private val TOP_APPS_COUNT_KEY = intPreferencesKey("top_apps_count")

    override val isAiSortingEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[AI_SORTING_KEY] ?: true // Enabled by default
        }

    override suspend fun setAiSortingEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[AI_SORTING_KEY] = enabled
        }
    }

    override val geminiApiKey: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[GEMINI_API_KEY] ?: ""
        }

    override suspend fun setGeminiApiKey(key: String) {
        context.dataStore.edit { preferences ->
            preferences[GEMINI_API_KEY] = key
        }
    }

    override val topAppsCount: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[TOP_APPS_COUNT_KEY] ?: 5
        }

    override suspend fun setTopAppsCount(count: Int) {
        context.dataStore.edit { preferences ->
            preferences[TOP_APPS_COUNT_KEY] = count.coerceIn(1, 10)
        }
    }
}
