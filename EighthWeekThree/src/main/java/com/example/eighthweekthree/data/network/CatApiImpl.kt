package com.example.eighthweekthree.data.network

import com.example.eighthweekthree.data.models.CatImageModel
import com.example.eighthweekthree.data.models.FavouriteCatImageModel
import com.example.eighthweekthree.data.models.FavouriteCatModel
import com.example.eighthweekthree.data.models.ResponseModel
import com.example.eighthweekthree.utils.Constants.Companion.API_HEADER
import com.example.eighthweekthree.utils.Constants.Companion.API_KEY
import com.example.eighthweekthree.utils.Constants.Companion.IMAGES
import com.example.eighthweekthree.utils.Constants.Companion.FAVOURITES
import io.ktor.client.*
import io.ktor.client.request.*

class CatApiImpl(private val client: HttpClient) : CatApi {

    override suspend fun getCatsImages(): List<CatImageModel> {
        return try {
            client.get {
                url(IMAGES)
                header(API_HEADER, API_KEY)
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
                header(API_HEADER, API_KEY)
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
                header(API_HEADER, API_KEY)
            }
        } catch (ex: Exception) {
            emptyList()
        }
    }
}