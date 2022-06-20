package com.example.fifthweekthree.data

import com.example.fifthweekthree.data.models.CatImageModel
import com.example.fifthweekthree.data.models.FavouriteCatImageModel
import com.example.fifthweekthree.data.models.FavouriteCatModel
import com.example.fifthweekthree.data.models.ResponseModel
import com.example.fifthweekthree.data.network.CatApi

class CatsRepository(private val catApi: CatApi) {

    suspend fun getCatsImages(): List<CatImageModel> {
        return catApi.getCatsImages()
    }

    suspend fun saveImageInFavourites(favourite: FavouriteCatModel): ResponseModel? {
        return catApi.saveImageInFavourites(favourite)
    }

    suspend fun getFavouriteCats(): List<FavouriteCatImageModel> {
        return catApi.getFavouriteCats()
    }

}