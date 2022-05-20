package com.example.fourthweek.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fourthweek.HomeRepository
import com.example.fourthweek.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var homeAdapter: HomeAdapter
    private lateinit var binding: FragmentHomeBinding
    private val homeRepository = HomeRepository()
    private lateinit var runnable: Runnable

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

        homeAdapter.setData(homeRepository.getChats(homeAdapter.getData()))

        binding.swipeToRefreshLayout.setOnRefreshListener {

            homeAdapter.setData(homeRepository.getChats(homeAdapter.getData()))

            binding.swipeToRefreshLayout.isRefreshing = false


        }

        return binding.root
    }




}