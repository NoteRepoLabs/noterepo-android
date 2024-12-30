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

package dev.noterepo.app.domain.usecases

import dev.noterepo.app.domain.models.SignUpRequest
import dev.noterepo.app.domain.models.SignUpResponse
import dev.noterepo.app.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor (private val authRepository: AuthRepository) {
    // SignUp function
    suspend fun signUp(request: SignUpRequest): Result<SignUpResponse> {
        return authRepository.signUp(request)
    }

    // SignIn
    // SignOut
}