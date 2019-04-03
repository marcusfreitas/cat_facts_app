package com.example.catfactsapp.repository.remote.result

sealed class ApiResult<out T: Any> {
    data class Success<out T: Any>(val data: T): ApiResult<T>()
    data class Error(val exception: Exception): ApiResult<Nothing>()
}