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
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.edit
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.noterepo.app.common.utils.dataStore
import dev.noterepo.app.data.local.preferences.PreferenceKeys
import dev.noterepo.app.presentation.screens.HomeScreen
import dev.noterepo.app.presentation.screens.OnboardingScreen
import dev.noterepo.app.presentation.screens.SignInScreen
import dev.noterepo.app.presentation.screens.SignUpCompleteScreen
import dev.noterepo.app.presentation.screens.SignUpScreen
import kotlinx.coroutines.launch

private val slideUpTransition: AnimatedContentTransitionScope<*>.() -> EnterTransition = {
    slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Up,
        animationSpec = tween(1000)
    )
}

@Composable
fun NoteRepoNavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screen.SignIn.route,
        modifier = modifier,
    ) {
        // Onboarding Screen
        composable(
            Screen.Onboarding.route,
            enterTransition = slideUpTransition
        ) {
            OnboardingScreen(onComplete = {
                scope.launch {

                    context.dataStore.edit { prefs ->
                        prefs[PreferenceKeys.ONBOARDING_COMPLETED] = true
                    }

                    navController.navigate(Screen.SignIn.route) {
                        popUpTo(Screen.Onboarding.route)
                    }

                }
            })
        }

        // SignIn Screen
        composable(
            route = Screen.SignIn.route,
            enterTransition = slideUpTransition
        ) { SignInScreen() }

        // SignUp Screen
        composable(Screen.SignUp.route) { SignUpScreen() }

        // SignUp Complete Screen
        composable(Screen.SignUpComplete.route) { SignUpCompleteScreen() }

        // Home Screen
        composable(
            route = Screen.Home.route,
            enterTransition = slideUpTransition
        ) { HomeScreen(navController = navController) }
    }
}