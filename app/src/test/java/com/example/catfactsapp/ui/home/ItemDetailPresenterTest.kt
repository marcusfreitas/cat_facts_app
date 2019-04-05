package com.example.catfactsapp.ui.home

import com.example.catfactsapp.domain.model.CatFactModel
import com.example.catfactsapp.ui.home.contract.ItemDetailContract
import com.example.catfactsapp.ui.home.presenter.ItemDetailPresenter
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.then
import org.junit.Before
import org.junit.Test

class ItemDetailPresenterTest {

    private lateinit var presenter: ItemDetailPresenter
    private val mockView: ItemDetailContract.View = mock()
    private val catFactModel = CatFactModel("funny fact", 200)

    @Before
    fun setUp() {
        presenter = ItemDetailPresenter()
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
    fun `share button click should ask view to open share intent`() {
        //given
        presenter.attach(mockView)

        //when
        presenter.shareButtonClick(catFactModel)

        //then
        then(mockView).should().shareCatFact(catFactModel)
    }

}