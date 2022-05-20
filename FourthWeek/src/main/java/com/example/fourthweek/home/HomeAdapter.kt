package com.example.fourthweek.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fourthweek.R
import com.example.fourthweek.models.ChatData

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    var oldList: ArrayList<ChatData> = ArrayList<ChatData>()



    inner class HomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val chatNameTv: TextView = itemView.findViewById(R.id.chatNameTv)
        val lastMessageTv: TextView = itemView.findViewById(R.id.lastMessageTv)
        val dateTimeTv: TextView = itemView.findViewById(R.id.dateTimeTv)
        val unreadCounterTv: TextView = itemView.findViewById(R.id.unreadCounterTv)

        fun bind(chat: ChatData?) {
            chat?.let {
                chatNameTv.text = it.chatName
                lastMessageTv.text = it.lastMessage
                dateTimeTv.text = it.dateTime
                unreadCounterTv.text = it.unreadCounter.toString()
            }
        }
    }

//    private val differCallback = object : DiffUtil.ItemCallback<ChatData>(){
//        override fun areItemsTheSame(
//            oldItem: ChatData,
//            newItem: ChatData
//        ): Boolean {
//            return oldItem.lastMessage == newItem.lastMessage &&
//                    oldItem.unreadCounter == newItem.unreadCounter
//        }
//
//        override fun areContentsTheSame(
//            oldItem: ChatData,
//            newItem: ChatData
//        ): Boolean {
//            return oldItem == newItem
//        }
//
//    }

    fun setData(newList: List<ChatData>){
        val diffCallback = HomeCallback(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        oldList.clear()
        oldList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)

    }

    fun getData(): List<ChatData> {
        return ArrayList(this.oldList)
    }

    //val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.chat_item,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val chat = oldList[position]
        holder.bind(chat)
    }

    override fun getItemCount(): Int {
        return oldList.size
    }



}