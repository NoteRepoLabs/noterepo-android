/**
 * 2025 - NoteRepo Engineering
 * Source Code for NoteRepo's Android App
 * GPL-v3 Open Source License
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.noterepo.app.R
import dev.noterepo.app.presentation.components.CustomTextField
import dev.noterepo.app.presentation.components.NoteRepoLogo
import dev.noterepo.app.presentation.ui.Typography

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
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
                NoteRepoLogo(size = 160)

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = stringResource(R.string.signup_intro),
                    style = Typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                CustomTextField(
                    value = "",
                    onValueChange = {},
                    type = "email",
                    placeholder = { Text(text = "Email Address", style = Typography.bodySmall) }
                )

                Spacer(modifier = Modifier.height(12.dp))

                CustomTextField(
                    value = "",
                    onValueChange = {},
                    type = "password",
                    placeholder = { Text(text = "Password", style = Typography.bodySmall) }
                )

                Spacer(modifier = Modifier.height(24.dp))

                FilledIconButton(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.onSurface
                    ),
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
