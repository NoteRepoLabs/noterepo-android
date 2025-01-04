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
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.noterepo.app.domain.models.ApiError
import dev.noterepo.app.domain.models.SignUpRequest
import dev.noterepo.app.domain.usecases.AuthUseCase
import dev.noterepo.app.presentation.state.SignUpUiState
import dev.noterepo.app.common.utils.emailRegex
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    // private val tag = "SignUp"
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

    fun signUp() {
        if (!_isEnabled.value) {
            _snackbarMessage.value =
                uiErrorMessage.value.ifEmpty { "Please fill all fields." }
            return
        }

        _uiState.value = SignUpUiState.Loading

        val request = SignUpRequest(
            email = emailAddress.value,
            password = password.value
        )

        viewModelScope.launch {
            val result = authUseCase.signUp(request)
            _uiState.value = when {
                result.isSuccess -> {
                    SignUpUiState.Success("Redirect to verify email")
                }

                result.isFailure -> {
                    val errorMessage = result.exceptionOrNull()?.message
                    val msg = try {
                        val res = Gson().fromJson(errorMessage, ApiError::class.java)
                        res.message
                    } catch (e: Exception) {
                        "Something went wrong"
                    }

                    println("msg: $msg")
                    _snackbarMessage.value = msg
                    SignUpUiState.Error(result.exceptionOrNull()?.message.orEmpty())
                }

                else -> SignUpUiState.Idle
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