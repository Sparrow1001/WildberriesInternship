package com.example.sevensweektwo.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroAPI {

    @GET("all.json")
    suspend fun getHeroById(): Response<List<HeroResponse>>
}