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

package dev.noterepo.app.data.remote

import dev.noterepo.app.data.models.SignUpRequestDTO
import dev.noterepo.app.data.models.SignUpResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/sign-up")
    suspend fun signUp(
        @Body requestDTO: SignUpRequestDTO
    ): Response<SignUpResponseDTO>
}