package dev.noterepo.app.domain.repositories

import dev.noterepo.app.domain.models.SignUpRequest
import dev.noterepo.app.domain.models.SignUpResponse

interface AuthRepository {
    suspend fun signUp(request: SignUpRequest): Result<SignUpResponse>
    // suspend fun signIn(request: SignInRequest): Result<SignInResponse>
}