package com.example.fifthweektwo.model

class HeroRepository(

) {
    suspend fun getHeroById(id: String) =
        RetrofitInstance.api.getHeroById(id)
}