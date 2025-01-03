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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import dev.noterepo.app.R

@Composable
fun EnvelopeAnimation(modifier: Modifier = Modifier) {
    val lightComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.email_anim_light))
    val darkComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.email_anim_dark))

    val composition = if (isSystemInDarkTheme()) lightComposition else darkComposition

    LottieAnimation(
        composition,
        restartOnPlay = true,
        modifier = modifier.size(180.dp)
    )
}