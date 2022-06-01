package com.example.fifthweekone.view.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.fifthweekone.HeroDTO
import com.example.fifthweekone.Repository

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