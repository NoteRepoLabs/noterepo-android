package dev.noterepo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import dev.noterepo.app.presentation.screens.OnboardingScreen
import dev.noterepo.app.presentation.ui.NoteRepoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
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
            NoteRepoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   OnboardingScreen(modifier = Modifier.padding((innerPadding)))
                }
            }
        }
    }
}
