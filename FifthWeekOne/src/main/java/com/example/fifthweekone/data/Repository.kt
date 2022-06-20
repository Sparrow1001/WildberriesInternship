package com.example.fifthweekone.data

import androidx.lifecycle.LiveData
import com.example.fifthweekone.data.network.HeroAPI

class Repository {
    private val heroApi = HeroAPI()
    fun getHeroFromApi(): LiveData<List<HeroDTO>> {
        return heroApi.getHeroesFromApi()
    }
}