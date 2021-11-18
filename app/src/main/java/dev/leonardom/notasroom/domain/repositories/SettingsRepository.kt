package dev.leonardom.notasroom.domain.repositories

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dev.leonardom.notasroom.data.data_store.SettingsDataStore
import dev.leonardom.notasroom.domain.utils.DataStoreKeys.Companion.DARK_MODE_KEY
import dev.leonardom.notasroom.domain.utils.DataStoreKeys.Companion.LAYOUT_MODE_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

const val SETTING_PREFERENCES_NAME: String = "settings_preferences"

class SettingsRepository(
    val context: Application
): SettingsDataStore {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        SETTING_PREFERENCES_NAME
    )

    override suspend fun toggleDarkMode() {
        context.dataStore.edit { preferences ->
            val current = preferences[DARK_MODE_KEY] ?: false
            preferences[DARK_MODE_KEY] = !current
        }
    }

    override suspend fun toggleNotesLayout() {
        context.dataStore.edit { preferences ->
            val current = preferences[LAYOUT_MODE_KEY] ?: false
            preferences[LAYOUT_MODE_KEY] = !current
        }
    }

    override fun readDarkModeValue(): Flow<Boolean> {
        return context.dataStore.data
            .catch { exception ->
                if(exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val darkMode = preferences[DARK_MODE_KEY] ?: false
                darkMode
            }
    }

    override fun readNotesLayoutValue(): Flow<Boolean> {
        return context.dataStore.data
            .catch { exception ->
                if(exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val layoutMode = preferences[LAYOUT_MODE_KEY] ?: false
                layoutMode
            }
    }

}