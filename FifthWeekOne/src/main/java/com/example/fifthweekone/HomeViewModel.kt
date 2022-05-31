package com.example.fifthweekone

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class HomeViewModel(
    app: Application,
    val repository: Repository
) : AndroidViewModel(app) {

    var hero: MutableLiveData<List<HeroDTO>> = MutableLiveData()

    init {
        getHeroes()
    }

    private fun getHeroes() {
        val response = repository.getHeroFromApi()

        hero = response as MutableLiveData<List<HeroDTO>>
    }

}