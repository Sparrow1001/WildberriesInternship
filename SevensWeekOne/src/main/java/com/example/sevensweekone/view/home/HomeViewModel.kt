package com.example.sevensweekone.view.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevensweekone.data.HeroDTO
import com.example.sevensweekone.data.Repository
import com.example.sevensweekone.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: Repository
) : ViewModel() {

    var hero: MutableLiveData<Resource<List<HeroDTO>>> = MutableLiveData()

    init {
        getHeroes()
    }

    private fun getHeroes() = viewModelScope.launch(Dispatchers.Default) {
        hero.postValue(Resource.Loading())
        val response = repository.getHeroes()
        Log.d("GGS4", response.toString())
        hero.postValue(Resource.Success(response))
    }

}