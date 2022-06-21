package com.example.fifthweekone.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fifthweekone.data.HeroDTO
import com.example.fifthweekone.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: Repository
) : ViewModel() {

    var hero: MutableLiveData<List<HeroDTO>> = MutableLiveData()

    init {
        getHeroes()
    }

    fun getHeroes()  {

        val response = repository.getHeroFromApi()

        hero = response as MutableLiveData<List<HeroDTO>>

    }

}