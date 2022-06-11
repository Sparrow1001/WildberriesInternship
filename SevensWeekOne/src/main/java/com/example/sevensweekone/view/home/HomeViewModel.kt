package com.example.sevensweekone.view.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sevensweekone.HeroDTO
import com.example.sevensweekone.Repository
import com.example.sevensweekone.utils.Resource

class HomeViewModel(
    app: Application,
    private val repository: Repository
) : AndroidViewModel(app) {

    var hero: MutableLiveData<Resource<List<HeroDTO>>> = MutableLiveData()

    init {
        getHeroes()
    }

    private fun getHeroes() {
        val response = repository.getHeroFromApi()

        hero = response as MutableLiveData<Resource<List<HeroDTO>>>
    }

}