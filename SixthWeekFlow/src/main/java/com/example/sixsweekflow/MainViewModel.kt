package com.example.sixsweekflow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val state: MutableLiveData<String> = MutableLiveData()

    fun updateState(newState: String) {
        state.postValue(newState)
    }

}