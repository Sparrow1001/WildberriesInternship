package com.example.eighthweekthree.view.favourite

import android.content.ContentValues
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
import com.example.eighthweekthree.R
import com.example.eighthweekthree.databinding.FragmentFavoriteBinding
import com.example.eighthweekthree.view.MainActivity
import com.example.eighthweekthree.utils.Resource


class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: FavouriteViewModel
    private lateinit var favouriteAdapter: FavouriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).favoriteViewModel
        setupRecyclerView()

        binding.goBackBt.setOnClickListener {
            findNavController().navigate(R.id.action_favoriteFragment_to_voteFragment)
        }

        refreshScreen()

        viewModel.favouriteCats.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { catResponse ->
                        favouriteAdapter.differ.submitList(catResponse)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(ContentValues.TAG, "An error occured: $message")
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                else -> {

                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavouriteCats()
    }

    private fun setupRecyclerView() {
        favouriteAdapter = FavouriteAdapter()
        binding.favouriteRv.adapter = favouriteAdapter
        binding.favouriteRv.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
    }

    private fun refreshScreen(){
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getFavouriteCats()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }


}