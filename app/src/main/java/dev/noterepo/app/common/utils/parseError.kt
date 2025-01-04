package dev.noterepo.app.common.utils

import com.google.gson.Gson
import dev.noterepo.app.data.models.ApiErrorDTO
import okhttp3.ResponseBody

fun parseError(errorBody: ResponseBody): ApiErrorDTO {
    return Gson().fromJson(errorBody.string(), ApiErrorDTO::class.java)
}