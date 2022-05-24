package com.example.fourthweek.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fourthweek.ChatService
import com.example.fourthweek.databinding.ChatListItemBinding
import com.example.fourthweek.databinding.LayoutFooterBinding
import com.example.fourthweek.models.ChatData

class HomeAdapter(

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_ITEMS = 1
        private const val TYPE_FOOTER = 2
    }
    private val chatService = ChatService()

    var chats: List<ChatData> = emptyList()
        set(newValue) {
            val diffCallback = HomeCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    override fun getItemViewType(position: Int): Int {
        if(position == chats.size) {
            return TYPE_FOOTER
        }
        return TYPE_ITEMS
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == TYPE_FOOTER){
            val binding = LayoutFooterBinding.inflate(inflater, parent, false)
            FooterViewHolder(binding)
        } else {
            val binding = ChatListItemBinding.inflate(inflater, parent, false)
            HomeViewHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_FOOTER){
            val footerVh: FooterViewHolder = holder as FooterViewHolder
            footerVh.binding.loadMoreBt.setOnClickListener {
                chats = chatService.getChats(chats)
            }
        }else{
            val homeVh: HomeViewHolder = holder as HomeViewHolder
            val chat = chats[position]
            homeVh.itemView.tag = chat
            with(homeVh.binding) {
                chatNameTv.text = chat.chatName
                lastMessageTv.text = chat.lastMessage
                dateTimeTv.text = chat.dateTime
                unreadCounterTv.text = chat.unreadCounter.toString()
            }

            homeVh.itemView.setOnClickListener {
                onItemClickListener?.let { it(chat) }
            }
        }


    }

    private var onItemClickListener: ((ChatData) -> Unit)? = null

    fun setOnItemClickListener(listener:(ChatData) -> Unit){
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return chats.size + 1
    }

    class HomeViewHolder(
        val binding: ChatListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class FooterViewHolder(
        val binding: LayoutFooterBinding
    ) : RecyclerView.ViewHolder(binding.root)




}

