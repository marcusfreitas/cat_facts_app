package com.example.catfactsapp.ui.home.contract

import com.example.catfactsapp.domain.model.CatFactModel

interface ItemDetailContract {
    interface View {
        fun shareCatFact(catFactModel: CatFactModel)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun shareButtonClick(catFactModel: CatFactModel)
    }
}