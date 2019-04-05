package com.example.catfactsapp.domain

import com.example.catfactsapp.domain.model.CatFactModel
import com.example.catfactsapp.repository.remote.catfacts.CatFactsApi
import io.reactivex.Observable

class CatFactUseCaseImpl(private val api: CatFactsApi) : CatFactUseCase {

    override fun getFacts(): Observable<List<CatFactModel>> = api.getFacts().map { response ->
        val result = arrayListOf<CatFactModel>()
        response.data.forEach {
            result.add(CatFactModel(it.fact, it.length))
        }

        return@map result
    }
}