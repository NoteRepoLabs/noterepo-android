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

package dev.noterepo.app.data.models

import com.google.gson.annotations.SerializedName
import dev.noterepo.app.common.models.UserRole

data class SignInRequestDTO(
    val email: String,
    val password: String,
)

data class SignInResponseDTO(
    val status: String,
    val statusCode: Int,
    val message: String,
    val payload: PayloadDTO?
) {
    data class PayloadDTO(
        val id: String,
        val username: String,
        val email: String,
        val bio: String,
        val isVerified: Boolean,
        val role: String,

        @SerializedName("refresh_token")
        val refreshToken: String,

        @SerializedName("access_token")
        val accessToken: String,

        val createdAt: String // Valid date string
    )
}

