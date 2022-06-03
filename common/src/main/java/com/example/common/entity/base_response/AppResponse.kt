package com.example.common.entity.base_response

import okhttp3.ResponseBody

sealed class AppResponse<T>(
    val data : T?,
    val error: Exception?,
    val code : Int?,
    val errorBody: ResponseBody?
) {
    companion object{
        fun <T> success(t : T): AppResponse<T> = AppResponseSuccess(t)
        fun <T> errorSystem(exc: Exception?): AppResponse<T> =
            AppResponseError(exc, AppResponseError.ERROR_SYSTEM,null)
        fun <T> errorBackend(statusCode : Int, body: ResponseBody?): AppResponse<T> =
            AppResponseError(null,statusCode,body)
        fun <T> loading(): AppResponse<T> = AppResponseLoading()
    }

}



class AppResponseSuccess<T>(
    data: T
) : AppResponse<T>(data,null,null,null)

class AppResponseError<T>(
    exc: Exception?,
    code: Int?,
    responseBody: ResponseBody?
) : AppResponse<T>(null,exc,code,responseBody){
    companion object{
        const val ERROR_SYSTEM = -1
    }
}

class AppResponseLoading<T> : AppResponse<T>(null,null,null,null)