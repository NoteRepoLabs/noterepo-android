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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import dev.noterepo.app.presentation.layout.ScreenLayout
import dev.noterepo.app.presentation.state.SignUpUiState
import dev.noterepo.app.presentation.ui.Typography
import dev.noterepo.app.presentation.ui.VibrantRed
import dev.noterepo.app.presentation.viewmodels.SignInViewModel
import dev.noterepo.app.util.emailRegex

@Composable
fun SignInScreen(modifier: Modifier = Modifier, viewModel: SignInViewModel = hiltViewModel()) {
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
    ) { innerPadding ->
        ScreenLayout(innerPadding = innerPadding, shouldCenter = true) {
            NoteRepoLogo(size = 160)

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.signin_intro),
                style = Typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Email address text field
            CustomTextField(
                value = emailAddress,
                onValueChange = viewModel::updateEmailAddress,
                type = "email",
                placeholder = { Text(text = "Email address", style = Typography.bodySmall) },
                isError = emailAddress.isNotEmpty() && !emailAddress.matches(emailRegex),
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Password text field
            CustomTextField(
                value = password,
                onValueChange = viewModel::updatePassword,
                type = "password",
                placeholder = { Text(text = "Password", style = Typography.bodySmall) },
                isError = password.isNotEmpty() && password.length < 8
            )

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

            // Sign In Button
            FilledIconButton(
                onClick = { /* sign in*/ },
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
                            trackColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.0F),
                            strokeWidth = 3.dp,
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                    Text(
                        text = if (uiState is SignUpUiState.Loading)
                            stringResource(R.string.signin_progress)
                        else
                            stringResource(R.string.signin_btn),
                        style = Typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.surface,
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Service acknowledgement
            Text(
                text = stringResource(R.string.acknowledgement),
                style = Typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}