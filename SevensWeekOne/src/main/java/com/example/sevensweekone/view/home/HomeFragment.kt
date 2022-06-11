package com.example.sevensweekone.view.home

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
import com.example.fifthweekone.view.home.HomeAdapter
import com.example.sevensweekone.MainActivity
import com.example.sevensweekone.R
import com.example.sevensweekone.databinding.FragmentHomeBinding
import com.example.sevensweekone.utils.Resource


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.hero.observe(viewLifecycleOwner, Observer { response ->

            when (response){
                is Resource.Success -> {
                    response.data.let { heroResponse ->
                        homeAdapter.differ.submitList(heroResponse)
                    }

                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }

        })

        homeAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("heroes", it)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_heroDetailsFragment,
                bundle
            )
        }
    }

    private fun setupRecyclerView(){
        homeAdapter = HomeAdapter()


        binding.dotaHeroesRv.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        binding.dotaHeroesRv.adapter = homeAdapter
    }


}