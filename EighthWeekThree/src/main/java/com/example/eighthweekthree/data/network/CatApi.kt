package com.example.eighthweekthree.data.network

import com.example.eighthweekthree.data.models.CatImageModel
import com.example.eighthweekthree.data.models.FavouriteCatImageModel
import com.example.eighthweekthree.data.models.FavouriteCatModel
import com.example.eighthweekthree.data.models.ResponseModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

interface CatApi {

    suspend fun getCatsImages(): List<CatImageModel>

    suspend fun saveImageInFavourites(favourite: FavouriteCatModel): ResponseModel?

    suspend fun getFavouriteCats(): List<FavouriteCatImageModel>

    companion object {
        fun create(): CatApi {
            return CatApiImpl(
                client = HttpClient(Android) {

                    install(Logging) {
                        level = LogLevel.ALL
                    }

                    install(JsonFeature) {
                        serializer = KotlinxSerializer(json)
                    }

                    install(HttpTimeout) {
                        requestTimeoutMillis = 25000L
                        connectTimeoutMillis = 25000L
                        socketTimeoutMillis = 25000L
                    }

                    defaultRequest {
                        if (method != HttpMethod.Get) contentType(ContentType.Application.Json)
                        accept(ContentType.Application.Json)
                    }
                }
            )
        }


        private val json = kotlinx.serialization.json.Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false
        }

    }

}




