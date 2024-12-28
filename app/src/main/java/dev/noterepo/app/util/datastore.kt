/**
 * 2025 - NoteRepo Engineering
 * Source Code for NoteRepo's Android App
 * GPL-v3 Open Source License
 */

package dev.noterepo.app.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name="onboarding_completed")