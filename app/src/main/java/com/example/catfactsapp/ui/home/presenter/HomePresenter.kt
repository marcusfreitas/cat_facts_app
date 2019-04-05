package com.example.catfactsapp.ui.home.presenter

import com.example.catfactsapp.domain.CatFactUseCase
import com.example.catfactsapp.domain.model.CatFactModel
import com.example.catfactsapp.schedulers.BaseSchedulerProvider
import com.example.catfactsapp.ui.home.contract.HomeContract
import io.reactivex.disposables.CompositeDisposable

class HomePresenter(
    private val useCase: CatFactUseCase,
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
        compositeDisposable.add(
            useCase.getFacts()
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .subscribe({ data ->
                view?.showData(data)
                view?.closeProgressDialog()
            }, {
                view?.closeProgressDialog()
                view?.showLoadDataError()
            })
        )
    }

    override fun onItemClick(item: CatFactModel) {
        view?.openDetailActivity(item)
    }
}