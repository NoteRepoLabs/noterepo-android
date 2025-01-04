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

package dev.noterepo.app.data.mappers

import dev.noterepo.app.common.models.UserRole
import dev.noterepo.app.common.utils.withDefaults
import dev.noterepo.app.data.models.SignInRequestDTO
import dev.noterepo.app.data.models.SignInResponseDTO
import dev.noterepo.app.domain.models.SignInRequest
import dev.noterepo.app.domain.models.SignInResponse

class SignInMapper {
    /**
     * Maps fields from signin API response to UI and viewmodel
     * usable states.
     */
    fun toDomain(responseDTO: SignInResponseDTO): SignInResponse {
        val payload = responseDTO.payload
        val response = payload?.let {
            SignInResponse(
                code = responseDTO.statusCode,
                id = it.id,
                email = it.email,
                username = it.username,
                bio = it.bio,
                role = UserRole.fromString(it.role),
                isVerified = it.isVerified,
                refreshToken = it.refreshToken,
                accessToken = it.accessToken
            )
        }
        return response.withDefaults()
    }

    /**
     * Maps email and password fields from UI and view model to
     * data layer API body (data transfer).
     */
    fun fromDomain(request: SignInRequest): SignInRequestDTO {
        return SignInRequestDTO(
            email = request.email,
            password = request.password
        )
    }
}