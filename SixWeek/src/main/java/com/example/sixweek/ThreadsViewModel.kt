package com.example.sixweek

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ThreadsViewModel: ViewModel() {

    val piNumber: MutableLiveData<String> = MutableLiveData()

    fun updateNumber(newNumber: String) {
        piNumber.postValue(newNumber)
    }

}