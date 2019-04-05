package com.example.catfactsapp.domain.injection

import com.example.catfactsapp.domain.CatFactUseCase
import com.example.catfactsapp.domain.CatFactUseCaseImpl
import com.example.catfactsapp.repository.injection.RemoteModule
import com.example.catfactsapp.repository.remote.catfacts.CatFactsApi
import dagger.Module
import dagger.Provides

@Module(includes = [RemoteModule::class])
class DomainModule {

    @Provides
    fun providesCatFactUseCase(api: CatFactsApi): CatFactUseCase = CatFactUseCaseImpl(api)
}
