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

package dev.noterepo.app.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dev.noterepo.app.presentation.state.SignUpUiState
import dev.noterepo.app.util.emailRegex
import javax.inject.Inject

class SignInViewModel @Inject constructor() : ViewModel() {
    private val _uiState = mutableStateOf<SignUpUiState>(SignUpUiState.Idle)
    private val _snackbarMessage = mutableStateOf<String?>(null)
    private val _emailAddress = mutableStateOf("")
    private val _password = mutableStateOf("")
    private val _uiErrorMessage = derivedStateOf {
        if (emailAddress.value.isNotEmpty() and !emailAddress.value.matches(emailRegex)) {
            return@derivedStateOf "Email address format is invalid"
        }
        if (password.value.isNotEmpty() and (password.value.length < 8)) {
            return@derivedStateOf "Password must be at least eight characters long"
        }

        return@derivedStateOf ""
    }
    private val _isEnabled = derivedStateOf {
        emailAddress.value.isNotEmpty() &&
                emailAddress.value.matches(emailRegex) &&
                password.value.length >= 8
    }

    val uiState: State<SignUpUiState> = _uiState
    val snackbarMessage: State<String?> = _snackbarMessage
    val emailAddress: State<String> = _emailAddress
    val password: State<String> = _password
    val uiErrorMessage: State<String> = _uiErrorMessage
    val isEnabled: State<Boolean> = _isEnabled

    fun updateEmailAddress(value: String) {
        _emailAddress.value = value
    }

    fun updatePassword(value: String) {
        _password.value = value
    }

    fun resetSnackbarMessage() {
        _snackbarMessage.value = null
    }
}