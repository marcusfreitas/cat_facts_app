package com.example.catfactsapp.repository.remote

import com.example.catfactsapp.repository.remote.datamodel.CatFactsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface CatFactsApi {

    @GET("/facts")
    fun getFacts(): Deferred<Response<CatFactsResponse>>

}