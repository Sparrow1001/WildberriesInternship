package com.example.sevensweektwo.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sevensweektwo.databinding.ItemSuperHeroBinding
import com.example.sevensweektwo.data.HeroResponse
import com.squareup.picasso.Picasso

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HeroViewHolder>() {

    inner class HeroViewHolder(val binding: ItemSuperHeroBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<HeroResponse>() {
        override fun areItemsTheSame(
            oldItem: HeroResponse,
            newItem: HeroResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: HeroResponse,
            newItem: HeroResponse
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSuperHeroBinding.inflate(inflater, parent, false)
        return HeroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val hero = differ.currentList[position]
        with(holder.binding) {
            heroNameTextView.text = hero.name

            Picasso.with(holder.itemView.context)
                .load(hero.images.sm)
                .into(heroImageView)

            holder.itemView.setOnClickListener {
                onItemClickListener?.let { it(hero) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((HeroResponse) -> Unit)? = null

    fun setOnItemClickListener(listener: (HeroResponse) -> Unit) {
        onItemClickListener = listener
    }

}