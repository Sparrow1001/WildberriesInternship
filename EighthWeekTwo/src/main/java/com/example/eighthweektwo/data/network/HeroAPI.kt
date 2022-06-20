package com.example.eighthweektwo.data.network

import com.example.eighthweektwo.data.HeroResponse
import retrofit2.Response
import retrofit2.http.GET

interface HeroAPI {
    @GET("all.json")
    suspend fun getHeroById(): Response<List<HeroResponse>>
}