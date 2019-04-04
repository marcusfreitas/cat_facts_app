package com.example.catfactsapp.ui.home.presenter

import com.example.catfactsapp.repository.remote.catfacts.CatFactsApi
import com.example.catfactsapp.repository.remote.catfacts.datamodel.FactDataModel
import com.example.catfactsapp.schedulers.BaseSchedulerProvider
import com.example.catfactsapp.ui.home.contract.HomeContract
import io.reactivex.disposables.CompositeDisposable

class HomePresenter(private val api: CatFactsApi,
                    private val schedulerProvider: BaseSchedulerProvider) : HomeContract.Presenter {

    var view: HomeContract.View? = null
    private val compositeDisposable = CompositeDisposable()

    override fun attach(view: HomeContract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
        compositeDisposable.clear()
    }

    override fun loadData() {
        view?.showProgressDialog()
        compositeDisposable.add(api.getFacts()
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .subscribe({ data ->
                view?.showData(data.all)
                view?.closeProgressDialog()
            }, {
                view?.closeProgressDialog()
                view?.showLoadDataError()
            })
        )
    }

    override fun onItemClick(item: FactDataModel) {
        view?.openDetailActivity(item)
    }
}