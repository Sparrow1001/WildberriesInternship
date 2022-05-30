package com.example.fifthweekthree.network

import com.example.fifthweekthree.models.CatImageModel
import com.example.fifthweekthree.models.VotesModel
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*

interface ApiService {

    suspend fun getCatImages(): List<CatImageModel>

    suspend fun sendVote(vote: VotesModel)

    companion object{
        fun create(): ApiService {
            return ApiServiceImpl(
                client = HttpClient(CIO){
                    install(Logging) {
                        level = LogLevel.ALL
                    }

                    install(ContentNegotiation){
                        json()
                    }


                    install(HttpTimeout) {
                        requestTimeoutMillis = 15000L
                        connectTimeoutMillis = 15000L
                        socketTimeoutMillis = 15000L
                    }

                }
            )
        }




    }
}