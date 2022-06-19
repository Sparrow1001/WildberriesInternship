package com.example.sevensweekthree.data

import com.example.sevensweekthree.data.database.CatsDatabase
import com.example.sevensweekthree.data.database.CatsEntity
import com.example.sevensweekthree.data.mappers.toLocal
import com.example.sevensweekthree.data.mappers.toRemote
import com.example.sevensweekthree.data.models.CatImageModel
import com.example.sevensweekthree.data.models.FavouriteCatImageModel
import com.example.sevensweekthree.data.models.FavouriteCatModel
import com.example.sevensweekthree.data.models.ResponseModel
import com.example.sevensweekthree.data.network.CatApi

class CatsRepository(
    private val catApi: CatApi,
    private val db: CatsDatabase
) {

    suspend fun getCatsImagesFromApi(): List<CatImageModel> {
        return catApi.getCatsImages()
    }

    suspend fun saveImageInFavouritesInApi(favourite: FavouriteCatModel): ResponseModel? {
        return catApi.saveImageInFavourites(favourite)
    }

    private suspend fun getFavouriteCatsFromApi(): List<FavouriteCatImageModel> {
        return catApi.getFavouriteCats()
    }

    private suspend fun saveImageInFavouritesLocal(cats: CatsEntity) = db.getCatsDao().upsert(cats)

    private fun getSavedCatsLocal() = db.getCatsDao().getAllCats()

    suspend fun getFavouritesCats(): List<FavouriteCatImageModel> {
        val localCats = getSavedCatsLocal().map { it.toRemote() }
        val remoteCats = getFavouriteCatsFromApi().map { it.toLocal() }

        return if (localCats.size != remoteCats.size || localCats.isEmpty()) {

            for (i in remoteCats) {
                saveImageInFavouritesLocal(i)
            }

            getSavedCatsLocal().map { it.toRemote() }

        } else {
            localCats
        }
    }


}