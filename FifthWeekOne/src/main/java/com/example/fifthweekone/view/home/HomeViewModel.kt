package com.example.fifthweekone.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fifthweekone.data.HeroDTO
import com.example.fifthweekone.data.Repository

class HomeViewModel(
    private val repository: Repository
) : ViewModel() {

    var hero: MutableLiveData<List<HeroDTO>> = MutableLiveData()

    init {
        getHeroes()
    }

    private fun getHeroes() {

        val response = repository.getHeroFromApi()

        hero = response as MutableLiveData<List<HeroDTO>>
    }

}