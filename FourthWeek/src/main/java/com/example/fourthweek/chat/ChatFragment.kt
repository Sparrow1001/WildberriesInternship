package com.example.fourthweek.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fourthweek.databinding.FragmentChatBinding


class ChatFragment : Fragment() {

    private lateinit var chatAdapter: ChatAdapter
    private lateinit var binding: FragmentChatBinding
    private lateinit var chatRepository: ChatRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentChatBinding.inflate(inflater, container, false)
        val lrLayoutManager = LinearLayoutManager(context)
        chatRepository = ChatRepository()
        lrLayoutManager.reverseLayout = true
        binding.chatDetailsRecyclerView.apply {
            layoutManager = lrLayoutManager
            chatAdapter = ChatAdapter()
            adapter = chatAdapter
        }

        binding.swipeToRefreshLayout.setOnRefreshListener {
            chatAdapter.chatDetails = chatRepository.updateList(chatAdapter.chatDetails)
            binding.swipeToRefreshLayout.isRefreshing = false
        }

        chatAdapter.chatDetails = chatRepository.getChatDetails(chatAdapter.chatDetails)


        // Inflate the layout for this fragment
        return binding.root
    }




}