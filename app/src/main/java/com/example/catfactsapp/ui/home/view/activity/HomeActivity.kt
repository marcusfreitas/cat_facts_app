package com.example.catfactsapp.ui.home.view.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.catfactsapp.R
import com.example.catfactsapp.repository.remote.catfacts.datamodel.FactDataModel
import com.example.catfactsapp.ui.home.contract.HomeContract
import com.example.catfactsapp.ui.home.view.adapter.CatFactsAdapter
import com.example.catfactsapp.ui.home.view.fragment.ItemDetailFragment
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeContract.View {

    @Inject lateinit var presenter: HomeContract.Presenter
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        setContentView(R.layout.activity_item_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        presenter.attach(this)

        presenter.loadData()
    }

    override fun showData(data: List<FactDataModel>) {
        val adapter = CatFactsAdapter(data)
        adapter.onClickListener = View.OnClickListener { v ->
            presenter.onItemClick(v.tag as FactDataModel)
        }
        item_list.adapter = adapter
    }

    override fun showProgressDialog() {
        progressDialog =  indeterminateProgressDialog(getString(R.string.progress_dialog_text),
            getString(R.string.progress_dialog_title))
        progressDialog?.show()
    }

    override fun closeProgressDialog() {
        progressDialog?.dismiss()
    }

    override fun showLoadDataError() {
        toast(getString(R.string.error_load_data))
    }

    override fun openDetailActivity(factDataModel: FactDataModel) {
        startActivity(intentFor<ItemDetailActivity>(ItemDetailFragment.ARG_ITEM to factDataModel))
    }
}
