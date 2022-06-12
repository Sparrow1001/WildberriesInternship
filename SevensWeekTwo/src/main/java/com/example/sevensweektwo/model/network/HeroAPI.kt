package com.example.sevensweektwo.model.network

import com.example.sevensweektwo.model.HeroResponse
import retrofit2.Response
import retrofit2.http.GET

interface HeroAPI {

    @GET("all.json")
    suspend fun getHeroById(): Response<List<HeroResponse>>
}