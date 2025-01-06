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

package dev.noterepo.app.common.utils

import com.google.gson.Gson
import dev.noterepo.app.domain.models.ApiError

fun parseError(errorBody: String?): ApiError {
    if (errorBody == null) return ApiError(
        statusCode = 500,
        message = "An unknown error occurred.",
        status = "unknown",
        path = "unknown"
    )
    return Gson().fromJson(errorBody, ApiError::class.java)
}