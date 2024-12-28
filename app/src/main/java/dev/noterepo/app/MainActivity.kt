package dev.noterepo.app

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.lifecycleScope
import dev.noterepo.app.data.local.preferences.PreferenceKeys
import dev.noterepo.app.presentation.screens.OnboardingScreen
import dev.noterepo.app.presentation.screens.SignUpScreen
import dev.noterepo.app.presentation.ui.NoteRepoTheme
import dev.noterepo.app.util.dataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val Context.dataStore by preferencesDataStore(name = "onboarding_preferences")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition { true }

        lifecycleScope.launch {
            delay(500)
            splashScreen.setKeepOnScreenCondition { false }
        }

        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            var showOnboarding by remember { mutableStateOf(false) }
            val scope = rememberCoroutineScope()

            LaunchedEffect(Unit) {
                showOnboarding =
                    !(context.dataStore.data.first()[PreferenceKeys.ONBOARDING_COMPLETED] ?: false)
            }

            NoteRepoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (showOnboarding) {
                        OnboardingScreen(
                            modifier = Modifier.padding((innerPadding)),
                            onComplete = {
                                scope.launch {
                                    context.dataStore.edit { prefs ->
                                        prefs[PreferenceKeys.ONBOARDING_COMPLETED] = true
                                    }
                                    showOnboarding = false
                                }
                            }
                        )
                    } else {
                        SignUpScreen()
                    }
                }
            }
        }
    }
}
