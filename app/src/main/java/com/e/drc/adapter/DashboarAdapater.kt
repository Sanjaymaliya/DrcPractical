package com.e.drc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e.drc.R
import com.e.drc.databinding.ItemDashboardBinding
import com.e.drc.model.NewsModel
import com.e.drc.ui.dashboard.DashboardNavigator


class DashboarAdapater(val context: Context, private var dashboardNavigator: DashboardNavigator, var newsList: ArrayList<NewsModel.Article>) : RecyclerView.Adapter<DashboarAdapater.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val profileSelectBinding = DataBindingUtil.inflate<ItemDashboardBinding>(
            LayoutInflater.from(parent.context), R.layout.item_dashboard, parent, false
        )
        return ViewHolder(profileSelectBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(position)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder internal constructor(private val itemDashboardBinding: ItemDashboardBinding) : RecyclerView.ViewHolder(itemDashboardBinding.root) {

        fun bind(position: Int) {

            itemDashboardBinding.txtTitle.text= newsList[position].title
            itemDashboardBinding.txtDes.text=newsList[position].description
            itemDashboardBinding.txtDate.text= newsList[position].publishedAt
            Glide.with(context)
                .load(newsList[position].urlToImage)
                .into( itemDashboardBinding.imgProduct)
            itemDashboardBinding.lytCvGames.setOnClickListener {
                dashboardNavigator.onClcik(newsList[position])
            }

        }

    }
}