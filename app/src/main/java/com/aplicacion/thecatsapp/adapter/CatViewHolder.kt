package com.aplicacion.thecatsapp.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.aplicacion.thecatsapp.data.model.Cat
import com.aplicacion.thecatsapp.databinding.ListItemCatBinding
import com.bumptech.glide.Glide


class CatViewHolder(
    private val itemBinding: ListItemCatBinding
) : RecyclerView.ViewHolder(itemBinding.root) {
    private lateinit var cat: Cat

    fun bind(item: Cat) {
        this.cat = item
        Glide.with(itemBinding.root)
            .load(item.getImgUrl(item.referenceImageId))
            .centerInside()
            .into(itemBinding.imageView)
        itemBinding.textViewName.text =  item.name
        itemBinding.textViewOrigin.text = "Origen: ${item.origin}"
        itemBinding.textViewIntelligence.text =  "Inteligencia: ${item.intelligence}"
    }

}