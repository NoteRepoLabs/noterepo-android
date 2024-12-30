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

data class SignUpRequestDTO(
    val email: String,
    val password: String
)

data class SignUpResponseDTO(
    val status: String,
    val statusCode: Int,
    val message: String,
    val payload: PayloadDTO?
) {
    data class PayloadDTO(
        val id: String,
        val email: String,
        val isVerified: Boolean,
        val createdAt: String
    )
}