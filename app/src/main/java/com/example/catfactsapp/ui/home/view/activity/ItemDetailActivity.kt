package com.example.catfactsapp.ui.home.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.catfactsapp.R
import com.example.catfactsapp.domain.model.CatFactModel
import com.example.catfactsapp.ui.home.contract.ItemDetailContract
import com.example.catfactsapp.ui.home.view.fragment.ItemDetailFragment
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_item_detail.*
import org.jetbrains.anko.share
import javax.inject.Inject

class ItemDetailActivity : AppCompatActivity(), ItemDetailContract.View {

    @Inject
    lateinit var presenter: ItemDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(detail_toolbar)

        presenter.attach(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            val fragment = ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(
                        ItemDetailFragment.ARG_ITEM,
                        intent.getParcelableExtra(ItemDetailFragment.ARG_ITEM)
                    )
                }
            }

            fab.setOnClickListener {
                presenter.shareButtonClick(intent.getParcelableExtra(ItemDetailFragment.ARG_ITEM))
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    override fun shareCatFact(catFactModel: CatFactModel) {
        share(catFactModel.fact, getString(R.string.cat_facts_share_subject))
    }
}
