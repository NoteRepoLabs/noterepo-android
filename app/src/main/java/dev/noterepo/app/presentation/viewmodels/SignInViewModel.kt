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
import dev.noterepo.app.domain.models.ApiException
import dev.noterepo.app.domain.models.SignInRequest
import dev.noterepo.app.domain.usecases.AuthUseCase
import dev.noterepo.app.presentation.UiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val tag = "SignInViewModel"

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
            try {
                val result = authUseCase.signIn(request)
                _uiState.value = when {
                    result.isSuccess -> {
                        // save details
                        Log.d(tag, "Successfully signed in.")
                        UiState.Success("Redirect to home screen")
                    }

                    result.isFailure -> {
                        when (val exception = result.exceptionOrNull()) {
                            is ApiException -> {
                                Log.d(tag, "API Error: ${exception.apiError}")

                                _snackbarMessage.value = exception.message
                                UiState.Error(exception.message ?: "Unknown exception")
                            }
                            else -> {
                                Log.d(tag, "Other Error: ${exception?.message ?: "Unknown exception"}")

                                _snackbarMessage.value = "An unknown error occurred."
                                UiState.Error("something went wrong!")
                            }
                        }
                    }

                    else -> UiState.Idle
                }
            } catch (e: HttpException) {
                Log.e(tag, "HTTP Error: ${e.message}")

                _uiState.value = UiState.Error("Network error occurred")
                _snackbarMessage.value = "Error connecting to the server."
            } catch (e: UnknownHostException) {
                Log.e(tag, "No Internet: ${e.message}")

                _uiState.value = UiState.Error("No internet connection")
                _snackbarMessage.value = "Please check your internet connection."
            } catch (e: Exception) {
                Log.e(tag, "Generic Error: ${e.message}")

                _uiState.value = UiState.Error("An unexpected error occurred")
                _snackbarMessage.value = "Something went wrong!"
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