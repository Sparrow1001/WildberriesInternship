package com.example.fourthweek.chat

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fourthweek.databinding.*
import com.example.fourthweek.models.ChatDetailsData

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_FROM = 1
        private const val TYPE_TO = 2
        private const val TYPE_HEADER = 3
    }

    private val chatRepository = ChatRepository()

    var chatDetails: List<ChatDetailsData> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int {
        val item = chatDetails[position]
        return if (position == chatDetails.size - 1){
            TYPE_HEADER
        } else if (item.sendType == 1) {
            TYPE_FROM
        } else{
            TYPE_TO
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == TYPE_TO) {
            val binding = ItemChatRightBinding.inflate(inflater, parent, false)
            ToViewHolder(binding)
        } else if (viewType == TYPE_FROM) {
            val binding = ItemChatLeftBinding.inflate(inflater, parent, false)
            FromViewHolder(binding)
        } else {
            val binding = LayoutHeaderBinding.inflate(inflater, parent, false)
            HeaderViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_TO){
            val toViewHolder: ToViewHolder = holder as ToViewHolder
            val chat = chatDetails[position]
            with(toViewHolder.binding) {
                nameTv.text = chat.name
                textMessageTv.text = chat.message
                timeTv.text = chat.dateTime
            }

        }else if (getItemViewType(position) == TYPE_FROM){
            val fromViewHolder: FromViewHolder = holder as FromViewHolder
            val chat = chatDetails[position]
            with(fromViewHolder.binding) {
                nameTv.text = chat.name
                textMessageTv.text = chat.message
                timeTv.text = chat.dateTime
            }

        } else {
            val headerViewHolder: HeaderViewHolder = holder as HeaderViewHolder
            headerViewHolder.binding.loadMoreBt.setOnClickListener {
                chatDetails = chatRepository.fillList(chatDetails)
            }
        }
    }

    override fun getItemCount(): Int {
        return chatDetails.size
    }

    class FromViewHolder(
        val binding: ItemChatLeftBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class ToViewHolder(
        val binding: ItemChatRightBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class HeaderViewHolder(
        val binding: LayoutHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root)
}