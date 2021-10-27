package dev.leonardom.notasroom.domain.repositories

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dev.leonardom.notasroom.data.data_store.SettingsDataStore
import kotlinx.coroutines.flow.Flow

const val SETTING_PREFERENCES_NAME: String = "settings_preferences"

class SettingsRepository(
    val context: Application
): SettingsDataStore {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        SETTING_PREFERENCES_NAME
    )

    override suspend fun toggleDarkMode() {
        TODO("Not yet implemented")
    }

    override suspend fun toggleNotesLayout() {
        TODO("Not yet implemented")
    }

    override fun readDarkModeValue(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun readNotesLayoutValue(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

}