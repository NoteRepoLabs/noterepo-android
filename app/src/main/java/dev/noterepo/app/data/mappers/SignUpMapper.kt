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

import dev.noterepo.app.common.utils.withDefaults
import dev.noterepo.app.data.models.SignUpRequestDTO
import dev.noterepo.app.data.models.SignUpResponseDTO
import dev.noterepo.app.domain.models.SignUpRequest
import dev.noterepo.app.domain.models.SignUpResponse

class SignUpMapper {
    /**
     * This converts an API response into a data object containing only required
     * fields for use in the UI.
     */
    fun toDomain(responseDTO: SignUpResponseDTO): SignUpResponse {
        val payload = responseDTO.payload
        val response = payload?.let {
            SignUpResponse(
                code = responseDTO.statusCode,
                id = it.id,
                email = it.email,
                isVerified = it.isVerified,
                createdAt = it.createdAt
            )
        }
        return response.withDefaults()
    }

    /**
     * This returns a sign up request data transfer object with values
     * captured from the UI and viewmodel.
     */
    fun fromDomain(request: SignUpRequest): SignUpRequestDTO {
        return SignUpRequestDTO(
            email = request.email,
            password = request.password
        )
    }
}