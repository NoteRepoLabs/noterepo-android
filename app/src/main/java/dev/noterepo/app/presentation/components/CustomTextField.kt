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

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.noterepo.app.R
import dev.noterepo.app.presentation.ui.VibrantRed

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    type: String = "text",
    isError: Boolean = false,
    leadingIcon: @Composable() (() -> Unit)? = null,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = if (isError) VibrantRed else MaterialTheme.colorScheme.outline,
            focusedBorderColor = if (isError) VibrantRed else MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = when (type) {
                "email" -> KeyboardType.Email
                "number" -> KeyboardType.Number
                else -> KeyboardType.Text
            }
        ),
        textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
        leadingIcon = leadingIcon
    )
}

@Composable
fun CustomPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    isPasswordVisible: Boolean = false,
    invertPasswordVisibility: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(8.dp),
        visualTransformation =
            if (isPasswordVisible) VisualTransformation.None
            else PasswordVisualTransformation(),
        textStyle = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = if (isError) VibrantRed else MaterialTheme.colorScheme.outline,
            focusedBorderColor = if (isError) VibrantRed else MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        trailingIcon = {
            IconButton(onClick = { invertPasswordVisibility() }) {
                if (isPasswordVisible) {
                    Icon(
                        painter = painterResource(R.drawable.icon_eye_slash),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6F),
                        modifier = Modifier.size(18.dp)
                    )
                } else {
                    Icon(
                        painter = painterResource(R.drawable.icon_eye_default),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6F),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
    )
}