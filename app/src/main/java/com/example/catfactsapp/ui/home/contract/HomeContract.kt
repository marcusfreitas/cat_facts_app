package com.example.catfactsapp.ui.home.contract

import com.example.catfactsapp.repository.remote.catfacts.datamodel.FactDataModel

interface HomeContract {

    interface View {
        fun showProgressDialog()
        fun closeProgressDialog()
        fun showData(data: List<FactDataModel>)
        fun openDetailActivity(factDataModel: FactDataModel)
        fun showLoadDataError()
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun loadData()
        fun onItemClick(item: FactDataModel)
    }
}