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

import dev.noterepo.app.domain.models.SignInRequest
import dev.noterepo.app.domain.models.SignInResponse
import dev.noterepo.app.domain.models.SignUpRequest
import dev.noterepo.app.domain.models.SignUpResponse
import dev.noterepo.app.domain.repositories.AuthRepository
import javax.inject.Inject

/**
 * Authentication usecases intended for use in view models only.
 */
class AuthUseCase @Inject constructor (private val authRepository: AuthRepository) {
    suspend fun signUp(request: SignUpRequest): Result<SignUpResponse> {
        return authRepository.signUp(request)
    }

    suspend fun signIn(request: SignInRequest): Result<SignInResponse> {
        return authRepository.signIn(request)
    }
}