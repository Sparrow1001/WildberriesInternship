package com.example.fifthweekone.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fifthweekone.view.MainActivity
import com.example.fifthweekone.R
import com.example.fifthweekone.databinding.FragmentHomeBinding


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
            homeAdapter.differ.submitList(response)
        })

        homeAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("hero", it)
            }
            findNavController().navigate(
                R.id.action_homeFragment_to_heroDetailsFragment,
                bundle
            )
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getHeroes()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setupRecyclerView() {
        homeAdapter = HomeAdapter()

        binding.dotaHeroesRv.layoutManager =
            GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        binding.dotaHeroesRv.adapter = homeAdapter
    }


}