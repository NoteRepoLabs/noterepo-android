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

package dev.noterepo.app.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.noterepo.app.R

@Composable
fun NoteRepoLogo(modifier: Modifier = Modifier, size: Int = 200) {
   when (isSystemInDarkTheme()) {
       true -> LightLogoVariant(modifier, size)
       else -> DarkLogoVariant(modifier, size)
   }
}

@Composable
private fun DarkLogoVariant(modifier: Modifier = Modifier, size: Int = 200) {
    Image(
        painter = painterResource(R.drawable.noterepo_logo_black),
        contentDescription = "NoteRepo",
        modifier = modifier.width(size.dp)
    )
}

@Composable
private fun LightLogoVariant(modifier: Modifier = Modifier, size: Int = 200) {
    Image(
        painter = painterResource(R.drawable.noterepo_logo_white),
        contentDescription = "NoteRepo",
        modifier = modifier.width(size.dp)
    )
}