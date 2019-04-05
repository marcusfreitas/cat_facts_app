package com.example.catfactsapp.domain

import com.example.catfactsapp.domain.model.CatFactModel
import io.reactivex.Observable

interface CatFactUseCase {
    fun getFacts(): Observable<List<CatFactModel>>
}