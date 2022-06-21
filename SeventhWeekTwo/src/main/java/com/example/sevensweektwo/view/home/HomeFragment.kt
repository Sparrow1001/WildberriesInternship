package com.example.sevensweektwo.view.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sevensweektwo.view.MainActivity
import com.example.sevensweektwo.R
import com.example.sevensweektwo.utils.Resource
import com.example.sevensweektwo.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var viewModel: HomeViewModel
    private var hasConnection: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()
        refreshScreen()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("heroes", it)
                Log.d("GGG", it.toString())
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_detailsFragment,
                bundle
            )
        }

        viewModel.superHeroes.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { heroResponse ->
                        homeAdapter.differ.submitList(heroResponse)
                    }
                    hasConnection = true
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }

                is Resource.NoInternet -> {
                    hideProgressBar()
                    response.data?.let { heroResponse ->
                        homeAdapter.differ.submitList(heroResponse)
                    }
                    if (hasConnection){
                        response.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                            Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG)
                                .show()
                            hasConnection = false
                        }
                    }

                }
            }
        })
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        homeAdapter = HomeAdapter()
        binding.superHeroRv.apply {
            adapter = homeAdapter
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        }
    }

    private fun refreshScreen() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getSuperHeroes()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }


}