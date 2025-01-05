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

import dev.noterepo.app.R

sealed class BottomNavigationItem(
    val route: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val label: String
) {
    data object Default : BottomNavigationItem(
        route = "home",
        selectedIcon = R.drawable.icon_home_bulk,
        unselectedIcon = R.drawable.icon_home_line,
        label = "Home"
    )

    data object Profile : BottomNavigationItem(
        route = "profile",
        selectedIcon = R.drawable.icon_user_bulk,
        unselectedIcon = R.drawable.icon_user_line,
        label = "Profile"
    )

    data object Settings : BottomNavigationItem(
        route = "settings",
        selectedIcon = R.drawable.icon_settings_bulk,
        unselectedIcon = R.drawable.icon_settings_line,
        label = "Settings"
    )

    companion object {
        val items = listOf(Default, Profile, Settings)
    }
}