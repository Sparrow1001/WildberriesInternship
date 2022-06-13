package com.example.sevensweekthree.data

import com.example.sevensweekthree.data.database.CatsDatabase
import com.example.sevensweekthree.data.database.CatsEntity
import com.example.sevensweekthree.data.models.CatImageModel
import com.example.sevensweekthree.data.models.FavouriteCatImageModel
import com.example.sevensweekthree.data.models.FavouriteCatModel
import com.example.sevensweekthree.data.models.ResponseModel
import com.example.sevensweekthree.data.network.CatApi

class CatsRepository(
    private val catApi: CatApi,
    val db: CatsDatabase
) {

    suspend fun getCatsImages(): List<CatImageModel> {
        return catApi.getCatsImages()
    }

    suspend fun saveImageInFavourites(favourite: FavouriteCatModel): ResponseModel? {
        return catApi.saveImageInFavourites(favourite)
    }

    suspend fun getFavouriteCats(): List<FavouriteCatImageModel> {
        return catApi.getFavouriteCats()
    }

    suspend fun upsert(cats: CatsEntity) = db.getCatsDao().upsert(cats)

    fun getSavedCats() = db.getCatsDao().getAllCats()

}