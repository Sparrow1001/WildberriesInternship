package com.example.fourthweek.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fourthweek.databinding.ChatItemBinding
import com.example.fourthweek.models.ChatData

class HomeAdapter(

) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(){

    var chats: List<ChatData> = emptyList()
        set(newValue) {
            val diffCallback = HomeCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChatItemBinding.inflate(inflater, parent, false)

        return HomeViewHolder(binding)


    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val chat = chats[position]
        holder.itemView.tag = chat
        with(holder.binding) {
            chatNameTv.text = chat.chatName
            lastMessageTv.text = chat.lastMessage
            dateTimeTv.text = chat.dateTime
            unreadCounterTv.text = chat.unreadCounter.toString()
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(chat) }
        }

    }

    private var onItemClickListener: ((ChatData) -> Unit)? = null

    fun setOnItemClickListener(listener:(ChatData) -> Unit){
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    class HomeViewHolder(
        val binding: ChatItemBinding
    ) : RecyclerView.ViewHolder(binding.root)




}

