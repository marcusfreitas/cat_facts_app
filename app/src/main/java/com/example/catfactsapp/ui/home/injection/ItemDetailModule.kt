package com.example.catfactsapp.ui.home.injection

import com.example.catfactsapp.ui.home.contract.ItemDetailContract
import com.example.catfactsapp.ui.home.presenter.ItemDetailPresenter
import dagger.Module
import dagger.Provides

@Module
class ItemDetailModule {

    @Provides
    fun providesItemDetailPresenter(): ItemDetailContract.Presenter = ItemDetailPresenter()
}