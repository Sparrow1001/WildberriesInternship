package com.example.fifthweekone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fifthweekone.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeAdapter = HomeAdapter()
        viewModel = (activity as MainActivity).viewModel

        binding.dotaHeroesRv.layoutManager = LinearLayoutManager(context)
        binding.dotaHeroesRv.adapter = homeAdapter

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


        return binding.root
    }


}