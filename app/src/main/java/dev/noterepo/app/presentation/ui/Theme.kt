package dev.noterepo.app.presentation.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    surface = Neutral900,
    onSurface = Neutral100,
    onSurfaceVariant = Neutral300,
    surfaceContainer = ModNeutral700,
    outline = Neutral600
)

private val LightColorScheme = lightColorScheme(
    surface = Neutral100,
    onSurface = Neutral900,
    onSurfaceVariant = Neutral500,
    surfaceContainer = ModNeutral200,
    outline = Neutral500
)

@Composable
fun NoteRepoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}