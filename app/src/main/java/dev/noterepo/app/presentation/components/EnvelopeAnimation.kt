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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import dev.noterepo.app.R

@Composable
fun EnvelopeAnimation() {
    val res = if (isSystemInDarkTheme()) R.raw.email_anim_light else R.raw.email_anim_dark
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(res))

    LottieAnimation(composition)
}