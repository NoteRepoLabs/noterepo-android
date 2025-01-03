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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.noterepo.app.R
import dev.noterepo.app.presentation.components.CustomTextField
import dev.noterepo.app.presentation.components.NoteRepoLogo
import dev.noterepo.app.presentation.state.SignUpUiState
import dev.noterepo.app.presentation.ui.Typography
import dev.noterepo.app.presentation.ui.VibrantRed
import dev.noterepo.app.presentation.viewmodels.SignUpViewModel
import dev.noterepo.app.util.emailRegex

@Composable
fun SignUpScreen(modifier: Modifier = Modifier, viewModel: SignUpViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState
    val emailAddress by viewModel.emailAddress
    val password by viewModel.password
    val uiErrorMessage by viewModel.uiErrorMessage
    val isEnabled by viewModel.isEnabled

    val snackbarMessage by viewModel.snackbarMessage
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.resetSnackbarMessage()
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    shape = RoundedCornerShape(12.dp)
                )
            }
        }
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
                    isError = emailAddress.isNotEmpty() && !emailAddress.matches(emailRegex),
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

                // UI Error message
                if (uiErrorMessage.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = uiErrorMessage,
                        style = MaterialTheme.typography.labelMedium,
                        color = VibrantRed,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                }

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
                    enabled = isEnabled,
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                         if (uiState is SignUpUiState.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(18.dp),
                                color = MaterialTheme.colorScheme.surface,
                                trackColor = MaterialTheme.colorScheme.surface.copy(alpha=0.0F),
                                strokeWidth = 3.dp,
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                         }
                        Text(
                            text = if (uiState is SignUpUiState.Loading)
                                stringResource(R.string.signup_progress)
                            else
                                stringResource(R.string.signup_btn),
                            style = Typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.surface,
                        )
                    }
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
