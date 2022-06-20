package com.example.fifthweekthree.view.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fifthweekthree.databinding.ItemFavouriteBinding
import com.example.fifthweekthree.data.models.FavouriteCatImageModel

class FavouriteAdapter : RecyclerView.Adapter<FavouriteAdapter.CatViewHolder>() {

    inner class CatViewHolder(val binding: ItemFavouriteBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<FavouriteCatImageModel>() {
        override fun areItemsTheSame(
            oldItem: FavouriteCatImageModel,
            newItem: FavouriteCatImageModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FavouriteCatImageModel,
            newItem: FavouriteCatImageModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFavouriteBinding.inflate(inflater, parent, false)
        return CatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val cat = differ.currentList[position]
        with(holder.binding) {

            catImage.setImageURI(cat.image.url)

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}