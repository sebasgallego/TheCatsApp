package com.aplicacion.thecatsapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aplicacion.thecatsapp.data.model.Cat
import com.aplicacion.thecatsapp.databinding.ListItemCatBinding

class CatAdapter : RecyclerView.Adapter<CatViewHolder>() {

    private var itemList: MutableList<Cat> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val binding: ListItemCatBinding =
            ListItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatViewHolder(binding)
    }

    /**
     * on Bind ViewHolder
     */
    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    /**
     * new Items
     */
    @SuppressLint("NotifyDataSetChanged")
    fun newItems(items: MutableList<Cat>) {
        this.itemList.clear()
        this.itemList.addAll(items)
        notifyDataSetChanged()
    }

    /**
     * get Item Count
     */
    override fun getItemCount(): Int = itemList.size
}