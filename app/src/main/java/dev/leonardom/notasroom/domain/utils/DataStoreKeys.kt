package dev.leonardom.notasroom.domain.utils

import androidx.datastore.preferences.core.booleanPreferencesKey

class DataStoreKeys {

    companion object {
        val DARK_MODE_KEY = booleanPreferencesKey("dev.leonardom.notasroom.DARK_MODE_KEY")
        val LAYOUT_MODE_KEY = booleanPreferencesKey("dev.leonardom.notasroom.LAYOUT_MODE_KEY")
    }

}