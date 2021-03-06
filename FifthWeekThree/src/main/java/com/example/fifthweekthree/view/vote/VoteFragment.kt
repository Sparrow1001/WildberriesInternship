package com.example.fifthweekthree.view.vote

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
import com.example.fifthweekthree.data.CatsRepository
import com.example.fifthweekthree.R
import com.example.fifthweekthree.databinding.FragmentVoteBinding
import com.example.fifthweekthree.data.network.CatApi
import com.example.fifthweekthree.utils.Resource
import com.example.fifthweekthree.view.MainActivity
import com.facebook.drawee.backends.pipeline.Fresco


class VoteFragment : Fragment() {

    private lateinit var binding: FragmentVoteBinding
    private lateinit var viewModel: VoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVoteBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).voteViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

        viewModel.catImage.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { catResponse ->
                        binding.catImage.setImageURI(catResponse.url)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                else -> {

                }
            }
        })
    }

    private fun setupListeners() {
        binding.likeBt.setOnClickListener {
            viewModel.getCatImages()
            viewModel.saveImageInFavorites()
        }

        binding.dislikeBt.setOnClickListener {
            viewModel.getCatImages()
        }

        binding.goToFavouritesBt.setOnClickListener {
            findNavController().navigate(R.id.action_voteFragment_to_favoriteFragment)
        }
    }


}