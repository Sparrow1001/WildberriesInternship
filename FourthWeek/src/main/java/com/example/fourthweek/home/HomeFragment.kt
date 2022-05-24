package com.example.fourthweek.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fourthweek.*
import com.example.fourthweek.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var homeAdapter: HomeAdapter
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeRepository: HomeRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.listNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        val lrLayoutManager = LinearLayoutManager(context)
        homeRepository = HomeRepository()
        binding.recyclerViewChatList.apply {
            layoutManager = lrLayoutManager
            homeAdapter = HomeAdapter()
            adapter = homeAdapter
        }

        homeAdapter.chats = homeRepository.getChats(homeAdapter.chats)


        binding.swipeToRefreshLayout.setOnRefreshListener {

            homeAdapter.chats = homeRepository.updateChats(homeAdapter.chats)

            binding.swipeToRefreshLayout.isRefreshing = false

        }

        homeAdapter.setOnItemClickListener {
            navController.navigate(R.id.action_homeFragment_to_chatFragment)
        }

        return binding.root
    }






}