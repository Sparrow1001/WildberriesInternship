package com.example.sixsweekflow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.sixsweekflow.databinding.FragmentTopBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


class TopFragment : Fragment() {

    private lateinit var binding: FragmentTopBinding
    private lateinit var viewModel: MainViewModel

    private var pi = ""
    private var counter = 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, Observer {
            setupUi(it)
        })
    }

    private fun setupUi(state: String) {
        when (state) {
            "play" -> {
                lifecycleScope.coroutineContext.cancelChildren()
                lifecycleScope.launch {
                    while (true) {
                        updateScreen()
                    }
                }
            }
            "pause" -> {
                lifecycleScope.coroutineContext.cancelChildren()
            }
            "reset" -> {
                counter = 3
                pi = ""
                binding.numberTV.text = "3.1"
                lifecycleScope.coroutineContext.cancelChildren()
                lifecycleScope.launch(Dispatchers.Main) {
                    updateScreen()
                }
            }
        }
    }

    private suspend fun updateScreen() {
        while (true) {
            piCounter(counter).collect{
                binding.numberTV.text = it
            }
            counter++
            delay(50)
        }
    }

    private fun piCounter(n: Int): Flow<String> = flow {
        val pi = StringBuilder()
        val boxes = n * 10 / 3
        val reminders = IntArray(boxes)
        for (i in 0 until boxes) {
            reminders[i] = 2
        }
        var heldDigits = 0
        for (i in 0 until n) {
            var carriedOver = 0
            var sum = 0
            for (j in boxes - 1 downTo 0) {
                reminders[j] *= 10
                sum = reminders[j] + carriedOver
                val quotient = sum / (j * 2 + 1)
                reminders[j] = sum % (j * 2 + 1)
                carriedOver = quotient * j
            }
            reminders[0] = sum % 10
            var q = sum / 10
            when (q) {
                9 -> {
                    heldDigits++
                }
                10 -> {
                    q = 0
                    for (k in 1..heldDigits) {
                        var replaced = pi.substring(i - k, i - k + 1).toInt()
                        if (replaced == 9) {
                            replaced = 0
                        } else {
                            replaced++
                        }
                        pi.deleteCharAt(i - k)
                        pi.insert(i - k, replaced)
                    }
                    heldDigits = 1
                }
                else -> {
                    heldDigits = 1
                }
            }
            pi.append(q)
        }
        if (pi.length >= 2) {
            pi.insert(1, '.')
        }
        emit(pi.toString())
    }.flowOn(Dispatchers.Default)


}