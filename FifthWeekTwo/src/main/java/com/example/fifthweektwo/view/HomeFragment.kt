package com.example.fifthweektwo.view

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fifthweektwo.MainActivity
import com.example.fifthweektwo.R
import com.example.fifthweektwo.Resource
import com.example.fifthweektwo.databinding.FragmentHomeBinding
import com.example.fifthweektwo.domain.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        homeAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("heroes", it)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_detailsFragment,
                bundle
            )
        }

        viewModel.superHeroes.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { heroResponse ->
                        homeAdapter.differ.submitList(heroResponse)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }

//                is Resource.NoInternet -> {
//                    hideProgressBar()
//                    response.liveData?.let { astroResponse ->
//                        astroResponse.observe(viewLifecycleOwner, Observer { response ->
//                            picturesAdapter.differ.submitList(response)
//                        })
//
//                    }
//                }
            }
        })


        return binding.root
    }

    private fun hideProgressBar(){
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        homeAdapter = HomeAdapter()
        binding.superHeroRv.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}