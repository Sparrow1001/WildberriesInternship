package com.example.sixweek

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.sixweek.R
import com.example.sixweek.databinding.FragmentTopThreadBinding


class TopThreadFragment : Fragment() {

    private lateinit var binding: FragmentTopThreadBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopThreadBinding.inflate(inflater, container, false)
        val viewModel = (activity as MainActivity).viewModel

        viewModel.piNumber.observe(viewLifecycleOwner, Observer {
            binding.numberTV.text = it
        })


        return binding.root
    }


}