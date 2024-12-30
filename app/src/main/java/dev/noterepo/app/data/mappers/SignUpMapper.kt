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

import dev.noterepo.app.data.models.ApiErrorDTO
import dev.noterepo.app.data.models.SignUpRequestDTO
import dev.noterepo.app.data.models.SignUpResponseDTO
import dev.noterepo.app.domain.models.ApiError
import dev.noterepo.app.domain.models.SignUpRequest
import dev.noterepo.app.domain.models.SignUpResponse

class SignUpMapper {
    fun toDomain(responseDTO: SignUpResponseDTO): SignUpResponse {
        val payload = responseDTO.payload
        return SignUpResponse(
            id = payload?.id.orEmpty(),
            email = payload?.email.orEmpty(),
            isVerified = payload?.isVerified ?: false,
            createdAt = payload?.createdAt.orEmpty()
        )
    }

    fun fromDomain(request: SignUpRequest): SignUpRequestDTO {
        return SignUpRequestDTO(
            email = request.email,
            password = request.password
        )
    }

    fun toErrorDomain(errorDTO: ApiErrorDTO): ApiError {
        return ApiError(
            path = errorDTO.path,
            message = errorDTO.message
        )
    }
}