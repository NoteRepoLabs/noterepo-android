package dev.noterepo.app.data.local.preferences

import androidx.datastore.preferences.core.booleanPreferencesKey

object PreferenceKeys {
    val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
}