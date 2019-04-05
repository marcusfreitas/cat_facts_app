package com.example.catfactsapp.ui.home.injection

import com.example.catfactsapp.domain.CatFactUseCase
import com.example.catfactsapp.domain.injection.DomainModule
import com.example.catfactsapp.schedulers.BaseSchedulerProvider
import com.example.catfactsapp.ui.home.contract.HomeContract
import com.example.catfactsapp.ui.home.presenter.HomePresenter
import dagger.Module
import dagger.Provides

@Module(includes = [DomainModule::class])
class HomeModule {

    @Provides
    fun providesHomePresenter(
        useCase: CatFactUseCase,
        schedulerProvider: BaseSchedulerProvider
    ): HomeContract.Presenter =
        HomePresenter(useCase, schedulerProvider)
}