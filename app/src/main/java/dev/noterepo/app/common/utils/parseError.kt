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