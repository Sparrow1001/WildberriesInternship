package com.example.sevensweektwo.data.network

import com.example.sevensweektwo.data.HeroResponse
import retrofit2.Response
import retrofit2.http.GET

interface HeroAPI {
    @GET("all.json")
    suspend fun getHeroById(): Response<List<HeroResponse>>
}