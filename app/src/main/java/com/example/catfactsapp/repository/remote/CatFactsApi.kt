package com.example.catfactsapp.repository.remote

import com.example.catfactsapp.repository.remote.datamodel.CatFactsResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface CatFactsApi {

    @GET("/facts")
    fun getFacts(): Observable<CatFactsResponse>

}