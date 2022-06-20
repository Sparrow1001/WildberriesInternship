package com.example.fourthweek.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fourthweek.*
import com.example.fourthweek.databinding.FragmentHomeBinding
import com.example.fourthweek.models.ChatData


class HomeFragment : Fragment() {

    private lateinit var homeAdapter: HomeAdapter
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeRepository: HomeRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupRecyclerView()

        homeAdapter.chats = homeRepository.getChats(homeAdapter.chats)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.swipeToRefreshLayout.setOnRefreshListener {

            val list = mutableListOf<ChatData>()
            list.addAll(homeRepository.updateChats(homeAdapter.chats))
            homeAdapter.chats = list

            binding.swipeToRefreshLayout.isRefreshing = false

        }

        homeAdapter.setOnItemClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_chatFragment)
        }

        binding.secondFrameBt.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_homeWithoutRecycleFragment)
        }
    }

    private fun setupRecyclerView(){
        val lrLayoutManager = LinearLayoutManager(context)
        homeRepository = HomeRepository()
        binding.recyclerViewChatList.apply {
            layoutManager = lrLayoutManager
            homeAdapter = HomeAdapter()
            adapter = homeAdapter
        }
    }


}