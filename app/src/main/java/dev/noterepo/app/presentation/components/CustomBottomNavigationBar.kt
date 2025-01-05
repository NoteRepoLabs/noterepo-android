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

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.noterepo.app.core.navigation.BottomNavigationItem
import dev.noterepo.app.core.navigation.Screen
import dev.noterepo.app.presentation.ui.VibrantGreen

@Composable
fun CustomBottomNavigationBar(modifier: Modifier = Modifier, navController: NavController) {
    NavigationBar(
        containerColor = Color.Transparent
    ) {
        val currentDestination = navController.currentDestination
        val currentRoute = currentDestination?.route ?: Screen.Home.route

        Log.d("NavigationBar", "current: $currentRoute")

        BottomNavigationItem.items.forEach { item ->
            NavigationBarItem(
                modifier = modifier,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(Screen.Home.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(
                            if (currentRoute == item.route) item.selectedIcon
                            else item.unselectedIcon
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                    )
                },
                label = { Text(item.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = VibrantGreen,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha=0.6F),
                    indicatorColor = VibrantGreen.copy(alpha=0.2F)
                )
            )
        }
    }
}