package com.example.fifthweekone

import androidx.lifecycle.LiveData
import com.example.fifthweekone.model.network.HeroAPI

class Repository {
    val heroApi = HeroAPI()
    fun getHeroFromApi(): LiveData<List<HeroDTO>> {
        return heroApi.getHeroesFromApi()
    }
}