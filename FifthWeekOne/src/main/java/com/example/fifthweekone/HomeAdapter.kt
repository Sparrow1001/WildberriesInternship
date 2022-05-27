package com.example.fifthweekone

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fifthweekone.databinding.ItemHeroBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HeroViewHolder>() {

    inner class HeroViewHolder(val binding: ItemHeroBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<HeroModel>(){
        override fun areItemsTheSame(
            oldItem: HeroModel,
            newItem: HeroModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: HeroModel,
            newItem: HeroModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}