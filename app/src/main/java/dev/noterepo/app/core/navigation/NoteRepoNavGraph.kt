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

package dev.noterepo.app.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.edit
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.noterepo.app.data.local.PreferenceKeys
import dev.noterepo.app.presentation.screens.OnboardingScreen
import dev.noterepo.app.presentation.screens.SignInScreen
import dev.noterepo.app.presentation.screens.SignUpCompleteScreen
import dev.noterepo.app.presentation.screens.SignUpScreen
import dev.noterepo.app.common.utils.dataStore
import kotlinx.coroutines.launch

@Composable
fun NoteRepoNavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = "signin",
        modifier = modifier,
    ) {
        composable(
            "onboarding",
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                    tween(1000)
                )
            }
        ) {
            OnboardingScreen(onComplete = {
                scope.launch {
                    context.dataStore.edit { prefs ->
                        prefs[PreferenceKeys.ONBOARDING_COMPLETED] = true
                    }
                    navController.navigate("signin") {
                        popUpTo("onboarding")
                    }
                }
            })
        }
        composable(
            route = "signin",
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                    tween(1000)
                )
            }
        ) { SignInScreen() }
        composable("signup") { SignUpScreen() }
        composable("signup_complete") { SignUpCompleteScreen() }
    }
}