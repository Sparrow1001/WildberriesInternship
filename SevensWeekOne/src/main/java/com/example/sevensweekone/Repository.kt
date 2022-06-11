package com.example.sevensweekone

import androidx.lifecycle.LiveData
import com.example.sevensweekone.model.network.HeroAPI
import com.example.sevensweekone.utils.Resource

class Repository {
    private val heroApi = HeroAPI()

    fun getHeroFromApi(): LiveData<Resource<List<HeroDTO>>> {
        return heroApi.getHeroesFromApi()
    }
}