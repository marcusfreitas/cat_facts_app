package com.example.catfactsapp.repository.injection

import com.example.catfactsapp.repository.remote.catfacts.CatFactsApi
import com.example.catfactsapp.schedulers.BaseSchedulerProvider
import com.example.catfactsapp.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetModule::class])
class RemoteModule {

    @Provides
    fun providesCatFactsApi(retrofit: Retrofit): CatFactsApi = retrofit.create(
        CatFactsApi::class.java)

    @Provides
    fun providesSchedulerPovider(): BaseSchedulerProvider = SchedulerProvider()
}