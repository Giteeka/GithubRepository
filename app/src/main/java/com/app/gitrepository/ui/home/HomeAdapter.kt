package com.app.gitrepository.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.gitrepository.R
import com.app.gitrepository.data.model.Repository
import com.app.gitrepository.databinding.RowItemBinding

class HomeAdapter(var list: List<Repository>?) : RecyclerView.Adapter<HomeAdapter.DataViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.row_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder?.rowItemBinding?.rowItem = list?.get(position)
        holder?.rowItemBinding?.executePendingBindings()
    }

    inner class DataViewHolder(var rowItemBinding: RowItemBinding) : RecyclerView.ViewHolder(rowItemBinding.root)

}