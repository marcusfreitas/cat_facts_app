package com.example.catfactsapp.repository.remote.catfacts

import com.example.catfactsapp.repository.remote.catfacts.datamodel.CatFactsResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface CatFactsApi {

    @GET("/facts")
    fun getFacts(): Observable<CatFactsResponse>

}