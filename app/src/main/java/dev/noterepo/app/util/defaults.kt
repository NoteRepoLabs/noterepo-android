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

package dev.noterepo.app.util

import dev.noterepo.app.common.UserRole
import dev.noterepo.app.domain.models.SignInResponse
import dev.noterepo.app.domain.models.SignUpResponse

/**
 * Returns sign up response with empty defaults
 */
fun SignUpResponse?.withDefaults(): SignUpResponse {
    return this ?: SignUpResponse(
        id = "",
        code = 0,
        email = "",
        isVerified = false,
        createdAt = ""
    )
}

/**
 * Returns sign in response with empty defaults
 */
fun SignInResponse?.withDefaults(): SignInResponse {
    return this ?: SignInResponse(
        id = "",
        code = 0,
        bio = "",
        email = "",
        username = "",
        isVerified = false,
        role = UserRole.USER,
        refreshToken = "",
        accessToken = ""
    )
}