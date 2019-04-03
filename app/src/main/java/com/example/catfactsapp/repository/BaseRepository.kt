package com.example.catfactsapp.repository

import com.example.catfactsapp.repository.remote.result.ApiResult
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

open class BaseRepository {
    suspend fun <T: Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {
        val result: ApiResult<T> = safeApiResult(call, errorMessage)
        var data: T? = null

        when(result) {
            is ApiResult.Success ->
                data = result.data
            is ApiResult.Error ->
                Timber.d("$errorMessage & Exception - ${result.exception}")
        }

        return data
    }

    private suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>, errorMessage: String) : ApiResult<T> {
        val response = call.invoke()
        if(response.isSuccessful) return ApiResult.Success(response.body()!!)

        return ApiResult.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}