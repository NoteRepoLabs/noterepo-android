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

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.noterepo.app.common.utils.emailRegex
import dev.noterepo.app.domain.models.SignInRequest
import dev.noterepo.app.domain.usecases.AuthUseCase
import dev.noterepo.app.presentation.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "SignInViewModel"

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _uiState = mutableStateOf<UiState>(UiState.Idle)
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
        emailAddress.value.isNotEmpty() && emailAddress.value.matches(emailRegex) && password.value.length >= 8
    }

    val uiState: State<UiState> = _uiState
    val snackbarMessage: State<String?> = _snackbarMessage
    val emailAddress: State<String> = _emailAddress
    val password: State<String> = _password
    val uiErrorMessage: State<String> = _uiErrorMessage

    fun signIn() {
        if (!_isEnabled.value) {
            _snackbarMessage.value =
                uiErrorMessage.value.ifEmpty { "Please fill all fields." }
            return
        }

        _uiState.value = UiState.Loading

        val request = SignInRequest(
            email = emailAddress.value,
            password = password.value
        )

        // Make API request with usecase
        viewModelScope.launch {
            val result = authUseCase.signIn(request)
            _uiState.value = when {
                result.isSuccess -> {
                    // save details
                    Log.d(TAG, "Successfully signed in.")
                    UiState.Success("Redirect to home screen")
                }

                result.isFailure -> {
                    val errorMessage = result.exceptionOrNull()?.message
                    Log.d(TAG, errorMessage ?: "NONE")
                    UiState.Error("Something went wrong")
                }

                else -> UiState.Idle
            }
        }
    }

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