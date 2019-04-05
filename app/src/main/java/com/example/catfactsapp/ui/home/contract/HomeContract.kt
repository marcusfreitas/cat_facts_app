package com.example.catfactsapp.ui.home.contract

import com.example.catfactsapp.domain.model.CatFactModel

interface HomeContract {

    interface View {
        fun showProgressDialog()
        fun closeProgressDialog()
        fun showData(data: List<CatFactModel>)
        fun openDetailActivity(catFactModel: CatFactModel)
        fun showLoadDataError()
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun loadData()
        fun onItemClick(item: CatFactModel)
    }
}