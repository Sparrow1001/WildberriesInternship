package com.example.sixsweekflow

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.sixsweekflow.databinding.FragmentBottomBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


class BottomFragment : Fragment() {

    private lateinit var binding: FragmentBottomBinding
    private lateinit var viewModel: MainViewModel

    private var seconds = 0
    private var minutes = 0
    private var hours = 0
    private var counter = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.playBt.setOnClickListener {
            lifecycleScope.coroutineContext.cancelChildren()
            viewModel.updateState("play")
            lifecycleScope.launch {
                setupUi()
            }
        }

        binding.pauseBt.setOnClickListener {
            viewModel.updateState("pause")
            lifecycleScope.coroutineContext.cancelChildren()
        }

        binding.resetBt.setOnClickListener {
            lifecycleScope.coroutineContext.cancelChildren()
            counter = 0
            minutes = 0
            seconds = 0
            binding.timeTv.text = "00:00:00"
            viewModel.updateState("reset")
            lifecycleScope.launch {
                setupUi()
            }
        }

    }

    private suspend fun setupUi() {
        while (true) {
            setupTimer().collect {
                binding.timeTv.text = it
            }

            if (counter % 20 == 0) {
                val rnd = java.util.Random()
                val color = Color.argb(
                    255,
                    rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256)
                )
                binding.root.setBackgroundColor(color)
            }
            counter++
            delay(1000)
        }
    }

    private fun setupTimer(): Flow<String> = flow {
        seconds = counter % 60
        minutes = counter / 60
        hours = minutes / 60

        var time = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        emit(time)
    }.flowOn(Dispatchers.IO)


}