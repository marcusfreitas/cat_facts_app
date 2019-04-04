package com.example.catfactsapp.ui.home.injection

import com.example.catfactsapp.repository.injection.RemoteModule
import com.example.catfactsapp.repository.remote.catfacts.CatFactsApi
import com.example.catfactsapp.schedulers.BaseSchedulerProvider
import com.example.catfactsapp.ui.home.contract.HomeContract
import com.example.catfactsapp.ui.home.presenter.HomePresenter
import dagger.Module
import dagger.Provides

@Module(includes = [RemoteModule::class])
class HomeModule {

    @Provides
    fun providesHomePresenter(api: CatFactsApi, schedulerProvider: BaseSchedulerProvider): HomeContract.Presenter =
        HomePresenter(api, schedulerProvider)
}