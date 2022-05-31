package com.example.fifthweekone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.fifthweekone.databinding.ItemHeroBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HeroViewHolder>() {

    inner class HeroViewHolder(val binding: ItemHeroBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<HeroDTO>(){
        override fun areItemsTheSame(
            oldItem: HeroDTO,
            newItem: HeroDTO
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: HeroDTO,
            newItem: HeroDTO
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHeroBinding.inflate(inflater, parent, false)
        return HeroViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val hero = differ.currentList[position]
        with(holder.binding){
            heroNameTextView.text = hero.localized_name
            heroImageView.load("https://api.opendota.com${hero.icon}")

            holder.itemView.setOnClickListener {
                onItemClickListener?.let { it(hero) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((HeroDTO) -> Unit)? = null

    fun setOnItemClickListener(listener:(HeroDTO) -> Unit){
        onItemClickListener = listener
    }

}