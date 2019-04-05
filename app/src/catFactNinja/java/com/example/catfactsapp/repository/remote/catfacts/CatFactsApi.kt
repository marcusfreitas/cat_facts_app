package com.example.catfactsapp.repository.remote.catfacts

import com.example.catfactsapp.repository.remote.catfacts.datamodel.CatFactsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CatFactsApi {

    @GET("/facts")
    fun getFacts(@Query("limit") limit: Int = 20): Observable<CatFactsResponse>
}