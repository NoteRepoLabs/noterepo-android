package dev.noterepo.app.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenManager @Inject constructor(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore(name = "auth_prefs")
        private val ACCESS_TOKEN_KEY = PreferenceKeys.ACCESS_TOKEN_KEY
        private val REFRESH_TOKEN_KEY = PreferenceKeys.REFRESH_TOKEN_KEY
        private val USER_ID_KEY = PreferenceKeys.USER_ID_KEY
        private val LAST_LOGIN_KEY = PreferenceKeys.LAST_LOGIN_KEY
    }

    val accessToken = context.dataStore.data.map { prefs -> prefs[REFRESH_TOKEN_KEY] }

    val refreshToken = context.dataStore.data.map { prefs -> prefs[REFRESH_TOKEN_KEY] }

    val userId = context.dataStore.data.map { prefs -> prefs[USER_ID_KEY] }

    val lastLogin = context.dataStore.data.map { prefs -> prefs[LAST_LOGIN_KEY] }

    // Updates authentication preferences on save.
    suspend fun saveTokens(accessToken: String, refreshToken: String, userId: String) {
        context.dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN_KEY] = accessToken
            prefs[REFRESH_TOKEN_KEY] = refreshToken
            prefs[USER_ID_KEY] = userId
            prefs[LAST_LOGIN_KEY] = System.currentTimeMillis()
        }
    }

    // Deletes all tokens in the datastore.
    suspend fun clearTokens() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }

}