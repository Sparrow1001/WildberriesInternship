package com.example.sevensweekone.view.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sevensweekone.HeroDTO
import com.example.sevensweekone.Repository
import com.example.sevensweekone.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    app: Application,
    private val repository: Repository
) : AndroidViewModel(app) {

    var hero: MutableLiveData<Resource<List<HeroDTO>>> = MutableLiveData()

    init {
        getHeroes()
    }

    private fun getHeroes() = viewModelScope.launch(Dispatchers.Default) {
        getResp()
    }

    private fun getResp(){
        val response = repository.getHeroes()
        Log.d("GGS4", response.toString())
        hero.postValue(Resource.Success(response))
    }

}