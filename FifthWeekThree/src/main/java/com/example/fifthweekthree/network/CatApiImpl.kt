package com.example.fifthweekthree.network

import com.example.fifthweekthree.utils.Constants.Companion.IMAGES
import com.example.fifthweekthree.models.CatImageModel
import com.example.fifthweekthree.models.FavouriteCatImageModel
import com.example.fifthweekthree.models.FavouriteCatModel
import com.example.fifthweekthree.models.ResponseModel
import com.example.fifthweekthree.utils.Constants.Companion.FAVOURITES
import io.ktor.client.*
import io.ktor.client.request.*

class CatApiImpl(private val client: HttpClient) : CatApi {

    override suspend fun getCatsImages(): List<CatImageModel> {
        return try {
            client.get {
                url(IMAGES)
                header("x-api-key", "fb6a7535-05e2-4e55-89bf-e662b09e6a74")
            }
        } catch (e: Exception) {
            println("Error: $e")
            emptyList()
        }
    }

    override suspend fun saveImageInFavourites(favourite: FavouriteCatModel): ResponseModel? {
        return try {
            client.post<ResponseModel> {
                url(FAVOURITES)
                header("x-api-key", "fb6a7535-05e2-4e55-89bf-e662b09e6a74")
                body = favourite
            }
        } catch (e: Exception) {
            return null
        }
    }

    override suspend fun getFavouriteCats(): List<FavouriteCatImageModel> {
        return try {
            client.get {
                url(FAVOURITES)
                header("x-api-key", "fb6a7535-05e2-4e55-89bf-e662b09e6a74")
            }
        } catch (ex: Exception) {
            emptyList()
        }
    }
}