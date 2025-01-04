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

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.noterepo.app.presentation.components.CustomAppBar
import dev.noterepo.app.presentation.layout.ScreenLayout

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            CustomAppBar(
                modifier = modifier
                    .statusBarsPadding()
                    .padding(8.dp),
                onDrawerBtnClicked = {}
            )
        }
    ) { innerPadding ->
        ScreenLayout(innerPadding = innerPadding, shouldCenter = true) {
            Text(text = "Welcome Home!", textAlign = TextAlign.Center)
        }
    }
}