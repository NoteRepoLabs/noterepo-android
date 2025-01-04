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

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
    val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
    val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    val USER_ID_KEY = stringPreferencesKey("user_id")
}