/**
 * 2025 - NoteRepo Engineering, Source Code for NoteRepo's Android App
 *
 *                     GNU GENERAL PUBLIC LICENSE
 *                        Version 3, 29 June 2007
 *
 *  Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
 *  Everyone is permitted to copy and distribute verbatim copies
 *  of this license document, but changing it is not allowed.
 *
 */

package dev.noterepo.app.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "auth_prefs")

@Singleton
class TokenManager @Inject constructor(private val context: Context) {

    companion object {
        private val ACCESS_TOKEN_KEY = PreferenceKeys.ACCESS_TOKEN_KEY
        private val REFRESH_TOKEN_KEY = PreferenceKeys.REFRESH_TOKEN_KEY
        private val USER_ID_KEY = PreferenceKeys.USER_ID_KEY
    }

    val accessToken = context.dataStore.data.map { prefs -> prefs[REFRESH_TOKEN_KEY] }

    val refreshToken = context.dataStore.data.map { prefs -> prefs[REFRESH_TOKEN_KEY] }

    val userId = context.dataStore.data.map { prefs -> prefs[USER_ID_KEY] }

    // Updates authentication preferences on save.
    suspend fun saveTokens(accessToken: String, refreshToken: String, userId: String) {
        context.dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN_KEY] = accessToken
            prefs[REFRESH_TOKEN_KEY] = refreshToken
            prefs[USER_ID_KEY] = userId
        }
    }

    // Deletes all tokens in the datastore.
    suspend fun clearTokens() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }

}