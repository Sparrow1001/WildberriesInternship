package com.example.eightsweekone.view.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.eightsweekone.R
import com.example.eightsweekone.data.HeroDTO
import com.example.eightsweekone.databinding.FragmentHomeBinding
import com.example.eightsweekone.utils.Resource
import com.example.eightsweekone.view.MainActivity
import com.example.eightsweekone.view.about.AboutAppFragment
import com.example.eightsweekone.view.details.HeroDetailsFragment


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

            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data.let { heroResponse ->
                        homeAdapter.differ.submitList(heroResponse)
                    }

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
            }

        })

        binding.mainToolBar.setNavigationOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.listNavHostFragment, AboutAppFragment())
                .addToBackStack(null)
                .commit()
        }

        homeAdapter.setOnItemClickListener {
            openDetailsFragment(it)
        }
    }

    private fun setupRecyclerView() {
        homeAdapter = HomeAdapter()
        binding.dotaHeroesRv.layoutManager =
            GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        binding.dotaHeroesRv.adapter = homeAdapter
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun openDetailsFragment(hero: HeroDTO){
        val bundle = Bundle().apply {
            putSerializable("heroes", hero)
        }
        val detailsFragment = HeroDetailsFragment()
        detailsFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.listNavHostFragment, detailsFragment)
            .addToBackStack(null)
            .commit()
    }


}