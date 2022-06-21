package com.example.sixweek

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sixweek.databinding.FragmentBottomThreadBinding
import java.util.*


class BottomThreadFragment : Fragment() {

    private var seconds = 0
    private var result = ""
    private var running = true

    private lateinit var binding: FragmentBottomThreadBinding
    private lateinit var viewModel: ThreadsViewModel
    private lateinit var handler: Handler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomThreadBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).viewModel


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playBt.setOnClickListener {
            startTimer()
        }

        binding.pauseBt.setOnClickListener {
            pauseTime()
        }

        binding.resetBt.setOnClickListener {
            resetTime()
        }

        Thread {
            Looper.prepare()
            handler = Handler(Looper.myLooper()!!)
            handler.postDelayed(timeUpdater, 1000)
            Looper.loop()
        }.start()
    }

    private val timeUpdater: Runnable = object : Runnable {
        override fun run() {
            val hours = seconds / 3600
            val minutes = seconds % 3600 / 60
            val secs = seconds % 60

            val time: String = String.format(
                Locale.getDefault(),
                "%d:%02d:%02d", hours,
                minutes, secs
            )

            activity?.runOnUiThread {
                binding.timeTv.text = time
            }

            viewModel.updateNumber(result)

            if (seconds != 0 && running && seconds % 20 == 0) {

                val rnd = Random()
                val color = Color.argb(
                    255,
                    rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256)
                )

                activity?.runOnUiThread {
                    binding.root.setBackgroundColor(color)
                }
            }

            if (running) {
                seconds++
                result = countPi(seconds)
            }

            handler.postDelayed(this, 1000)
        }
    }

    private fun startTimer() {
        running = true
    }

    private fun resetTime() {
        running = false
        seconds = 0
        result = ""
    }

    private fun pauseTime() {
        running = false
    }

    private fun countPi(n: Int): String {
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
        return pi.toString()
    }


}