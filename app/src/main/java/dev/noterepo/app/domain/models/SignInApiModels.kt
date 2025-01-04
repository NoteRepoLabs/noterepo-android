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

package dev.noterepo.app.domain.models

import dev.noterepo.app.common.models.UserRole

data class SignInRequest(
    val email: String,
    val password: String
)

data class SignInResponse(
    val code: Int,
    val id: String,
    val username: String,
    val email: String,
    val bio: String,
    val isVerified: Boolean,
    val role: UserRole,
    val refreshToken: String,
    val accessToken: String,
)