package com.example.fifthweektwo.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroAPI {

    @GET("{id}")
    suspend fun getHeroById(
        @Path("id") id: String
    ): Response<HeroResponse>

}