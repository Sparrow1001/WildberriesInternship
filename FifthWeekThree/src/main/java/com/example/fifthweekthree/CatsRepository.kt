package com.example.fifthweekthree

import com.example.fifthweekthree.models.CatImageModel
import com.example.fifthweekthree.models.FavouriteCatImageModel
import com.example.fifthweekthree.models.FavouriteCatModel
import com.example.fifthweekthree.models.ResponseModel
import com.example.fifthweekthree.network.CatApi

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