package com.example.fifthweektwo.data

import com.example.fifthweektwo.data.network.RetrofitInstance

class HeroRepository {
    suspend fun getHeroById() =
        RetrofitInstance.api.getHeroById()
}