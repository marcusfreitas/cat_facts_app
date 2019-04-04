package com.example.catfactsapp.ui.home

import com.example.catfactsapp.repository.remote.catfacts.CatFactsApi
import com.example.catfactsapp.repository.remote.catfacts.datamodel.CatFactsResponse
import com.example.catfactsapp.repository.remote.catfacts.datamodel.FactDataModel
import com.example.catfactsapp.repository.remote.catfacts.datamodel.NameDataModel
import com.example.catfactsapp.repository.remote.catfacts.datamodel.UserDataModel
import com.example.catfactsapp.schedulers.TrampolineSchedulerProvider
import com.example.catfactsapp.ui.home.contract.HomeContract
import com.example.catfactsapp.ui.home.presenter.HomePresenter
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.then
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class HomePresenterTest {

    private lateinit var presenter: HomePresenter

    private val mockView: HomeContract.View = mock()
    private val mockApi: CatFactsApi = mock()
    private val schedulerProvider = TrampolineSchedulerProvider()
    private val factList = listOf(
        FactDataModel("funny cat fact", 10, UserDataModel(NameDataModel("Marcus", "Freitas"))),
        FactDataModel("even funnier cat fact", 50, UserDataModel(NameDataModel("Marcus", "Freitas"))),
        FactDataModel("the funniest cat fact ever!", 1000, UserDataModel(NameDataModel("Marcus", "Freitas")))
    )
    private val response = CatFactsResponse(all = factList)

    @Before
    fun setUp() {
        presenter = HomePresenter(mockApi, schedulerProvider)
    }

    @Test
    fun `successfully attach view`() {
        //when
        presenter.attach(mockView)

        //then
        assert(presenter.view != null)
    }

    @Test
    fun `successfully detach view`() {
        //when
        presenter.detach()

        //then
        assert(presenter.view == null)
    }

    @Test
    fun `successfully load data`() {
        //given
        whenever(mockApi.getFacts()).thenReturn(Observable.just(response))
        presenter.attach(mockView)

        //when
        presenter.loadData()

        //then
        then(mockView).should().showProgressDialog()
        then(mockApi).should().getFacts()
        then(mockView).should().showData(response.all)
        then(mockView).should().closeProgressDialog()
    }

    @Test
    fun `load data fails`() {
        //given
        whenever(mockApi.getFacts()).thenReturn(Observable.error(Throwable("Load data failed")))
        presenter.attach(mockView)

        //when
        presenter.loadData()

        //then
        then(mockView).should().showProgressDialog()
        then(mockApi).should().getFacts()
        then(mockView).should().showLoadDataError()
        then(mockView).should().closeProgressDialog()
    }
}