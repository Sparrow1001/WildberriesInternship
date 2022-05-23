package com.example.fourthweek.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.fourthweek.R
import com.example.fourthweek.databinding.ChatItemBinding
import com.example.fourthweek.models.ChatData

interface ChatActionListener {
    fun onChatUpdate()

    fun onChatDetails(chat: ChatData)
}

class HomeAdapter(
    private val actionListener: ChatActionListener
) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(), View.OnClickListener {

    var chats: List<ChatData> = emptyList()
        set(newValue) {
            val diffCallback = HomeCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onClick(v: View) {
        val chat = v.tag as ChatData
        actionListener.onChatDetails(chat)
    }

    //val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChatItemBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
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

    }

    override fun getItemCount(): Int {
        return chats.size
    }

    class HomeViewHolder(
        val binding: ChatItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

//    override fun onRefresh() {
//        actionListener.onChatUpdate()
//
//    }


}

