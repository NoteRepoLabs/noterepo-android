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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.noterepo.app.R
import dev.noterepo.app.presentation.components.CustomAppBar
import dev.noterepo.app.presentation.components.CustomBottomNavigationBar
import dev.noterepo.app.presentation.components.CustomTextField
import dev.noterepo.app.presentation.layout.ScreenLayout
import dev.noterepo.app.presentation.ui.Neutral900
import dev.noterepo.app.presentation.ui.Typography
import dev.noterepo.app.presentation.ui.VibrantGreen

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(
        modifier = modifier.statusBarsPadding(),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            CustomAppBar(
                modifier = Modifier.padding(horizontal = 8.dp),
                onDrawerBtnClicked = {}
            )
        },
        bottomBar = { CustomBottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        ScreenLayout(innerPadding = innerPadding) {
            // Search bar
            CustomTextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        text = stringResource(R.string.home_searchbar),
                        style = Typography.bodySmall,
                        fontSize = 15.sp
                    )
                },
                type = "text",
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.icon_search),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // New Repo Button
            FilledIconButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = VibrantGreen
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_plus),
                        contentDescription = null,
                        tint = Neutral900,
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = stringResource(R.string.home_newrepo),
                        color = Neutral900,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}