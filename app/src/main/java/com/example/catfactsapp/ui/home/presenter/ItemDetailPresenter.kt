package com.example.catfactsapp.ui.home.presenter

import com.example.catfactsapp.domain.model.CatFactModel
import com.example.catfactsapp.ui.home.contract.ItemDetailContract

class ItemDetailPresenter : ItemDetailContract.Presenter {

    var view: ItemDetailContract.View? = null

    override fun attach(view: ItemDetailContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    override fun shareButtonClick(catFactModel: CatFactModel) {
        view?.shareCatFact(catFactModel)
    }
}