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

package dev.noterepo.app.data.repositories

import dev.noterepo.app.common.utils.parseError
import dev.noterepo.app.data.mappers.SignInMapper
import dev.noterepo.app.data.mappers.SignUpMapper
import dev.noterepo.app.data.remote.api.ApiService
import dev.noterepo.app.domain.models.ApiException
import dev.noterepo.app.domain.models.SignInRequest
import dev.noterepo.app.domain.models.SignInResponse
import dev.noterepo.app.domain.models.SignUpRequest
import dev.noterepo.app.domain.models.SignUpResponse
import dev.noterepo.app.domain.repositories.AuthRepository
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val signUpMapper: SignUpMapper,
    private val signInMapper: SignInMapper
) : AuthRepository {
    // Implementation for sign up requests.
    override suspend fun signUp(request: SignUpRequest): Result<SignUpResponse> {
        return try {
            val requestDTO = signUpMapper.fromDomain(request)
            val response = apiService.signUp(requestDTO)

            if (response.isSuccessful) {
                Result.success(signUpMapper.toDomain(response.body()!!))
            } else {
                val errorBody = response.errorBody()?.string()
                val apiError = parseError(errorBody)

                // update error code to match response
                apiError.statusCode = response.code()

                Result.failure(ApiException(apiError))
            }
        } catch (e: HttpException) {
            val error = parseError(e.response()?.errorBody()?.toString())
            error.statusCode = e.code()

            Result.failure(ApiException(error))
        }
    }

    // Implementation for sign in requests.
    override suspend fun signIn(request: SignInRequest): Result<SignInResponse> {
        return try {
            val requestDTO = signInMapper.fromDomain(request)
            val response = apiService.signIn(requestDTO)

            if (response.isSuccessful) {
                Result.success(signInMapper.toDomain(response.body()!!))
            } else {
                val errorBody = response.errorBody()?.string()
                val apiError = parseError(errorBody)

                // update error code to match response
                apiError.statusCode = response.code()

                Result.failure(ApiException(apiError))
            }
        } catch (e: HttpException) {
            val error = parseError(e.response()?.errorBody()?.toString())
            error.statusCode = e.code()

            Result.failure(ApiException(error))
        }
    }
}