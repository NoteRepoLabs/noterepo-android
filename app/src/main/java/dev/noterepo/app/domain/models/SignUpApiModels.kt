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

data class SignUpRequest(
    val email: String,
    val password: String
)

data class SignUpResponse(
    val id: String,
    val email: String,
    val isVerified: Boolean,
    val createdAt: String
)