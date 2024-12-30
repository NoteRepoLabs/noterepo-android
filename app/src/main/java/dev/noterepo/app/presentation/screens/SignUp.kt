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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.noterepo.app.R
import dev.noterepo.app.presentation.components.CustomTextField
import dev.noterepo.app.presentation.components.NoteRepoLogo
import dev.noterepo.app.presentation.ui.Typography
import dev.noterepo.app.presentation.viewmodels.SignUpViewModel
import dev.noterepo.app.util.emailRegex

@Composable
fun SignUpScreen(modifier: Modifier = Modifier, viewModel: SignUpViewModel = hiltViewModel()) {
    val emailAddress by viewModel.emailAddress
    val password by viewModel.password
    val isEnabled by viewModel.isEnabled

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = modifier
                    .padding(12.dp)
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // NoteRepo Logo
                NoteRepoLogo(size = 160)

                Spacer(modifier = Modifier.height(24.dp))

                // Sign Up Screen Intro
                Text(
                    text = stringResource(R.string.signup_intro),
                    style = Typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Email Address Text Field
                CustomTextField(
                    value = emailAddress,
                    onValueChange = viewModel::updateEmailAddress,
                    type = "email",
                    placeholder = { Text(text = "Email Address", style = Typography.bodySmall) },
                    isError = emailAddress.isNotEmpty() && !emailAddress.matches(emailRegex)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Password Text Field
                CustomTextField(
                    value = password,
                    onValueChange = viewModel::updatePassword,
                    type = "password",
                    placeholder = { Text(text = "Password", style = Typography.bodySmall) },
                    isError = password.isNotEmpty() && password.length < 8
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Sign Up Button
                FilledIconButton(
                    onClick = { viewModel.signUp() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.onSurface
                    ),
                    enabled = isEnabled
                ) {
                    Text(
                        text = stringResource(R.string.signup_btn),
                        style = Typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.surface,
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.acknowledgement),
                    style = Typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
