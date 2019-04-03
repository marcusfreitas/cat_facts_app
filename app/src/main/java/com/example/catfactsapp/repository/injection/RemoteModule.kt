package com.example.catfactsapp.repository.injection

import com.example.catfactsapp.repository.remote.CatFactsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetModule::class])
class RemoteModule {

    @Provides
    fun providesCatFactsApi(retrofit: Retrofit): CatFactsApi = retrofit.create(CatFactsApi::class.java)
}