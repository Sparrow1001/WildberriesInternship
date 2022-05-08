package com.example.additionaltask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.example.additionaltask.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBt.setOnClickListener {
            binding.pgBar.visibility = View.VISIBLE
            CoroutineScope(IO).launch {
                val res = chooseSort()
                withContext(Main){
                    setText(res.toString())
                    binding.pgBar.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun chooseSort(): Double {

        var start = 0.0
        var end = 0.0
        var res = 0.0

        val array = IntArray(binding.countEt.text.toString().toInt())
        for (i in array.indices){
            array[i] = Random().nextInt(100)
        }

        if (binding.spinner.selectedItem == "Bogo Sort"){
            val a = BogoSort()
            a.bogosort(array)
        } else if (binding.spinner.selectedItem == "Shell Sort"){

            start = Calendar.getInstance().timeInMillis.toDouble()
            shellSort(array)
            end = Calendar.getInstance().timeInMillis.toDouble()
            res = ((end - start)/1000.0)
        } else if (binding.spinner.selectedItem == "Quick Sort"){
            start = Calendar.getInstance().timeInMillis.toDouble()
            quickSort(array, 0, array.size-1)
            end = Calendar.getInstance().timeInMillis.toDouble()
            res = ((end - start)/1000.0)
        } else if (binding.spinner.selectedItem == "Patience Sort"){
            start = Calendar.getInstance().timeInMillis.toDouble()
            patienceSort(array)
            end = Calendar.getInstance().timeInMillis.toDouble()
            res = ((end - start)/1000.0)
        } else if (binding.spinner.selectedItem == "Gnome Sort"){
            start = Calendar.getInstance().timeInMillis.toDouble()
            gnomeSort(array)
            end = Calendar.getInstance().timeInMillis.toDouble()
            res = ((end - start)/1000.0)
        } else if (binding.spinner.selectedItem == "Standard Sort"){
            start = Calendar.getInstance().timeInMillis.toDouble()
            array.sort()
            end = Calendar.getInstance().timeInMillis.toDouble()
            res = ((end - start)/1000.0)
        }

    return res

    }

    fun quickSort(array: IntArray, left: Int, right: Int) {
        val index = partition (array, left, right)
        if(left < index-1) {
            quickSort(array, left, index-1)
        }
        if(index < right) {
            quickSort(array,index, right)
        }
    }

    fun partition(array: IntArray, l: Int, r: Int): Int {
        var left = l
        var right = r
        val pivot = array[(left + right)/2]
        while (left <= right) {
            while (array[left] < pivot) left++
            while (array[right] > pivot) right--
            if (left <= right) {
                swapArray(array, left,right)
                left++
                right--
            }
        }
        return left
    }

    fun swapArray(a: IntArray, b: Int, c: Int) {
        val temp = a[b]
        a[b] = a[c]
        a[c] = temp
    }

    fun  gnomeSort(arr: IntArray, ascending: Boolean = true) {
        var i = 1
        var j = 2
        while (i < arr.size)
            if (ascending && (arr[i - 1] <= arr[i]) ||
                !ascending && (arr[i - 1] >= arr[i]))
                i = j++
            else {
                val temp = arr[i - 1]
                arr[i - 1] = arr[i]
                arr[i--] = temp
                if (i == 0) i = j++
            }
    }

    fun shellSort(arr: IntArray): Int {
        val n = arr.size

        var gap = n / 2
        while (gap > 0) {

            var i = gap
            while (i < n) {

                val temp = arr[i]
                var j = i
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap]
                    j -= gap
                }
                arr[j] = temp
                i += 1
            }
            gap /= 2
        }
        return 0
    }

    fun patienceSort(arr: IntArray) {
        if (arr.size < 2) return
        val piles = mutableListOf<MutableList<Int>>()
        outer@ for (el in arr) {
            for (pile in piles) {
                if (pile.last() > el) {
                    pile.add(el)
                    continue@outer
                }
            }
            piles.add(mutableListOf(el))
        }

        for (i in 0 until arr.size) {
            var min = piles[0].last()
            var minPileIndex = 0
            for (j in 1 until piles.size) {
                if (piles[j].last() < min) {
                    min = piles[j].last()
                    minPileIndex = j
                }
            }
            arr[i] = min
            val minPile = piles[minPileIndex]
            minPile.removeAt(minPile.lastIndex)
            if (minPile.size == 0) piles.removeAt(minPileIndex)
        }
    }

    fun printArray(arr: IntArray) {
        val lastIndex: Int = arr.size - 1
        for (i in 0..lastIndex) {
            val num = arr[i]
            print("$num ")
        }
        println("")
    }

    private fun setText(text: String) {
        binding.timeEt.text = "$text"
    }


}