package com.example.additionaltask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.additionaltask.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import java.util.*

class MainActivity : AppCompatActivity() {

    //Не ставить в BogoSort больше, чем 13 элементов, а лучше вообще его не трогать)!!

    //Стандартная функция сортировки почти всегда работает быстрее остальных, поэтому нет
    //особого смысла в реализации отдельной сортировки.

    //Инструкция:
    //Для использования сортировки, нужно ввести количество символов, которые будут сортироваться.
    //После этого в выпадающем меню нужно выбрать сортировку.
    //Как только это сделано, нажимаем на кнопку Start и ждём
    //Когда исчезнет значёк загрузки, появится время, за которое отработала сортировка.


    /*
    На небольших значениях все алгоритмы отрабатывают очень быстро, но стандартная всё равно быстрее

    На 100 тыс. значений стандартная отрабатывает в среднем за 0.003 с. Примерно за то же время
    отрабатывает Merge Sort. Шелла и быстрая сортировка работают в среднем за 0.008 c. Остальные от
    6 до 15 секунд.

    1 млн. значиений: Шелла в среднем за 0.11, быстрая за 0.09, Merge Sort 0.098. Стандартная здесь
    снова быстрее всех и работает в срезнем за 0.033
     */

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
        } else if (binding.spinner.selectedItem == "Merge Sort"){
            start = Calendar.getInstance().timeInMillis.toDouble()
            mergeSort(array)
            end = Calendar.getInstance().timeInMillis.toDouble()
            res = ((end - start)/1000.0)
        } else if (binding.spinner.selectedItem == "Selection Sort"){
            start = Calendar.getInstance().timeInMillis.toDouble()
            selectionSort(array)
            end = Calendar.getInstance().timeInMillis.toDouble()
            res = ((end - start)/1000.0)
        }

    return res

    }

    ///////////////////

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

///////////////////

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

    ///////////////////

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

    ///////////////////

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

    ///////////////////

    fun mergeSort(array : IntArray, helper:IntArray = IntArray(array.size), low:Int = 0, high : Int = array.size-1) {
        if(low < high) {
            val middle:Int = (low+high)/2
            mergeSort(array, helper, low, middle)
            mergeSort(array, helper, middle+1, high)
            merge(array, helper, low, middle, high)
        }
    }

    fun merge (array: IntArray, helper: IntArray, low: Int, middle:Int, high: Int){

        for(i in low..high) helper[i] = array[i]

        var helperLeft = low
        var helperRight = middle + 1
        var current = low



        while (helperLeft <= middle && helperRight <= high) {
            if(helper[helperLeft] <= helper[helperRight]) {
                array[current] = helper[helperLeft]
                helperLeft++
            } else {
                array[current] = helper[helperRight]
                helperRight++
            }
            current++
        }


        val remaining = middle - helperLeft
        for (i in 0..remaining) {
            array[current + i] = helper[helperLeft + i]
        }

    }

    ///////////////////

    fun selectionSort(arr:IntArray){
        var n= arr.size
        var temp:Int
        for(i in 0..n-1){
            var indexOfMin = i
            for(j in n-1 downTo  i){
                if(arr[j]< arr[indexOfMin])
                    indexOfMin=j
            }
            if(i!=indexOfMin){
                temp = arr[i]
                arr[i]= arr[indexOfMin]
                arr[indexOfMin]=temp
            }
        }
    }

    ///////////////////

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