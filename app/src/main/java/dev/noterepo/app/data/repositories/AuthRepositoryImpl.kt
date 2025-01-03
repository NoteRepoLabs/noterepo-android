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

import dev.noterepo.app.data.mappers.SignUpMapper
import dev.noterepo.app.data.remote.ApiService
import dev.noterepo.app.domain.models.SignUpRequest
import dev.noterepo.app.domain.models.SignUpResponse
import dev.noterepo.app.domain.repositories.AuthRepository
import dev.noterepo.app.util.parseError
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val signUpMapper: SignUpMapper
) : AuthRepository {
    override suspend fun signUp(request: SignUpRequest): Result<SignUpResponse> {
        return try {
            val requestDTO = signUpMapper.fromDomain(request)
            val response = apiService.signUp(requestDTO)

            if (response.isSuccessful) {
                Result.success(signUpMapper.toDomain(response.body()!!))
            } else {
                Result.failure(Exception(response.errorBody()?.string()))
            }
        } catch (e: HttpException) {
            val errorDTO = e.response()?.errorBody()?.let {
                parseError(it)
            }
            val serverMessage = signUpMapper.toErrorDomain(errorDTO!!)

            Result.failure(Exception(signUpMapper.toErrorDomain(errorDTO!!).message))
        }
    }
}