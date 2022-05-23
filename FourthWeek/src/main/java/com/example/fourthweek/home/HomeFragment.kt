package com.example.fourthweek.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fourthweek.App
import com.example.fourthweek.ChatService
import com.example.fourthweek.ChatsListener
import com.example.fourthweek.HomeRepository
import com.example.fourthweek.databinding.FragmentHomeBinding
import com.example.fourthweek.models.ChatData


class HomeFragment : Fragment() {

    private lateinit var homeAdapter: HomeAdapter
    private lateinit var binding: FragmentHomeBinding
    private val homeRepository = HomeRepository()

    private val chatService: ChatService
        get() = (activity?.applicationContext as App).chatService


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.recyclerViewChatList.apply {
            layoutManager = LinearLayoutManager(context)
            homeAdapter = HomeAdapter()
            adapter = homeAdapter
        }

        //homeAdapter.setData(homeRepository.getChats(homeAdapter.getData()))

        binding.swipeToRefreshLayout.setOnRefreshListener {

            //homeAdapter.setData(homeRepository.getChats(homeAdapter.getData()))

            chatService.getChats()
            chatService.addListener(chatsListener)

            binding.swipeToRefreshLayout.isRefreshing = false


        }

        homeAdapter.setOnItemClickListener {
            chatService.deleteChat(it)
        }

        chatService.addListener(chatsListener)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        chatService.removeListener(chatsListener)
    }

    private val chatsListener: ChatsListener = {
        homeAdapter.chats = it
    }


}