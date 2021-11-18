package dev.leonardom.notasroom.data.data_store

import kotlinx.coroutines.flow.Flow

interface SettingsDataStore {

    suspend fun toggleDarkMode()

    suspend fun toggleNotesLayout()

    fun readDarkModeValue(): Flow<Boolean>

    fun readNotesLayoutValue(): Flow<Boolean>

}