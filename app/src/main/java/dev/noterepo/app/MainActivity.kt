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

package dev.noterepo.app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.noterepo.app.core.navigation.NoteRepoNavGraph
import dev.noterepo.app.data.local.PreferenceKeys
import dev.noterepo.app.presentation.screens.OnboardingScreen
import dev.noterepo.app.presentation.ui.NoteRepoTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val Context.dataStore by preferencesDataStore(name = "onboarding_prefs")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()
        var keepSplashScreenOn = true

        splashScreen.setKeepOnScreenCondition { keepSplashScreenOn }

        enableEdgeToEdge()

        setContent {
            val context = LocalContext.current
            val navController = rememberNavController()
            val scope = rememberCoroutineScope()
            var showOnboarding by remember {mutableStateOf<Boolean?>(null)}

            // Resolve onboarding state in splash screen phase
            LaunchedEffect(Unit) {
                val prefs = context.dataStore.data.first()
                showOnboarding = !(prefs[PreferenceKeys.ONBOARDING_COMPLETED] ?: false)
                keepSplashScreenOn = false
            }

            // Use Application NavGraph to determine first screen
            NoteRepoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   when (showOnboarding) {
                       null -> {}
                       true -> OnboardingScreen(modifier = Modifier.padding(innerPadding),
                           onComplete = {
                               scope.launch {
                                   context.dataStore.edit { prefs ->
                                       prefs[PreferenceKeys.ONBOARDING_COMPLETED] = true
                                   }
                                   showOnboarding = false
                               }
                           })
                       false ->  NoteRepoNavGraph(
                           modifier = Modifier.padding(innerPadding),
                           navController = navController
                       )
                   }
                }
            }
        }
    }
}
