package com.example.fifthweektwo.data.network

import com.example.fifthweektwo.data.HeroResponse
import retrofit2.Response
import retrofit2.http.GET

interface HeroAPI {

    @GET("all.json")
    suspend fun getHeroById(): Response<List<HeroResponse>>

}