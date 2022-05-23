package com.example.fourthweek.home

import androidx.recyclerview.widget.DiffUtil
import com.example.fourthweek.models.ChatData

class HomeCallback(private val oldList: List<ChatData>, private val newList: List<ChatData>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}