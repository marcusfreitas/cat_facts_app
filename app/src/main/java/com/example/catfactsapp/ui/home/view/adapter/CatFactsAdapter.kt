package com.example.catfactsapp.ui.home.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.catfactsapp.R
import com.example.catfactsapp.repository.remote.catfacts.datamodel.FactDataModel
import kotlinx.android.synthetic.main.item_list_content.view.*

class CatFactsAdapter (
    private val values: List<FactDataModel>) :
    RecyclerView.Adapter<CatFactsAdapter.ViewHolder>() {

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.factText.text = item.text
        holder.factUpvotes.text = item.upvotes.toString()

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val factText: TextView = view.fact_text
        val factUpvotes: TextView = view.fact_upvotes
    }
}