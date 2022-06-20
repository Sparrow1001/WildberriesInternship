package com.example.fourthweek.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fourthweek.databinding.FragmentChatBinding


class ChatFragment : Fragment() {

    private lateinit var chatAdapter: ChatAdapter
    private lateinit var binding: FragmentChatBinding
    private lateinit var chatRepository: ChatRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentChatBinding.inflate(inflater, container, false)
        setupRecyclerView()
        chatRepository = ChatRepository()


        binding.swipeToRefreshLayout.setOnRefreshListener {
            chatAdapter.chatDetails = chatRepository.updateList(chatAdapter.chatDetails)
            binding.swipeToRefreshLayout.isRefreshing = false
        }

        binding.mainToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        chatAdapter.chatDetails = chatRepository.getChatDetails(chatAdapter.chatDetails)

        return binding.root
    }

    private fun setupRecyclerView() {
        val lrLayoutManager = LinearLayoutManager(context)
        lrLayoutManager.reverseLayout = true
        binding.chatDetailsRecyclerView.apply {
            layoutManager = lrLayoutManager
            chatAdapter = ChatAdapter()
            adapter = chatAdapter
        }
    }


}