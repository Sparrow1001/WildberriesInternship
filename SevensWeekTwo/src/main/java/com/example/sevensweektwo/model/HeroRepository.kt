package com.example.sevensweektwo.model

class HeroRepository {
    suspend fun getHeroById() =
        RetrofitInstance.api.getHeroById()
}