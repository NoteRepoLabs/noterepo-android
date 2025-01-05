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

package dev.noterepo.app.presentation.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import dev.noterepo.app.R
import dev.noterepo.app.presentation.components.EnvelopeAnimation
import dev.noterepo.app.presentation.layout.ScreenLayout

@Composable
fun SignUpCompleteScreen(modifier: Modifier = Modifier) {
    Scaffold(containerColor = MaterialTheme.colorScheme.surface) { innerPadding ->
        ScreenLayout(modifier = modifier, innerPadding = innerPadding, shouldCenter = true) {
            EnvelopeAnimation()
            Text(
                text = stringResource(R.string.signupcomplete_message),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5F),
                textAlign = TextAlign.Center
            )
        }
    }
}