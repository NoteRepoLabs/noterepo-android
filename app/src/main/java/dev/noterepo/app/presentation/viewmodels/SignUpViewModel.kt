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
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.noterepo.app.util.emailRegex
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    private val tag = "SignUp"

    private val _emailAddress = mutableStateOf("")
    private val _password = mutableStateOf("")
    private val _isEnabled = derivedStateOf {
        emailAddress.value.isNotEmpty() &&
                emailAddress.value.matches(emailRegex) &&
                password.value.length >= 8
    }

    val emailAddress: State<String> = _emailAddress
    val password: State<String> = _password
    val isEnabled: State<Boolean> = _isEnabled

    fun signUp() {

    }

    fun updateEmailAddress(value: String) {
        _emailAddress.value = value
    }

    fun updatePassword(value: String) {
        _password.value = value
    }

}