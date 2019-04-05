package com.example.catfactsapp.ui.home

import com.example.catfactsapp.domain.CatFactUseCase
import com.example.catfactsapp.domain.model.CatFactModel
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
    private val mockUseCase: CatFactUseCase = mock()
    private val schedulerProvider = TrampolineSchedulerProvider()
    private val factList = listOf(
        CatFactModel("funny cat fact", 10),
        CatFactModel("even funnier cat fact", 50),
        CatFactModel("the funniest cat fact ever!", 1000)
    )

    @Before
    fun setUp() {
        presenter = HomePresenter(mockUseCase, schedulerProvider)
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
        whenever(mockUseCase.getFacts()).thenReturn(Observable.just(factList))
        presenter.attach(mockView)

        //when
        presenter.loadData()

        //then
        then(mockView).should().showProgressDialog()
        then(mockUseCase).should().getFacts()
        then(mockView).should().showData(factList)
        then(mockView).should().closeProgressDialog()
    }

    @Test
    fun `load data fails`() {
        //given
        whenever(mockUseCase.getFacts()).thenReturn(Observable.error(Throwable("Load data failed")))
        presenter.attach(mockView)

        //when
        presenter.loadData()

        //then
        then(mockView).should().showProgressDialog()
        then(mockUseCase).should().getFacts()
        then(mockView).should().showLoadDataError()
        then(mockView).should().closeProgressDialog()
    }
}