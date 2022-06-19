package com.example.eighthweekthree.data.mappers

import com.example.eighthweekthree.data.database.CatsEntity
import com.example.eighthweekthree.data.models.CatImageModel
import com.example.eighthweekthree.data.models.FavouriteCatImageModel

fun FavouriteCatImageModel.toLocal(): CatsEntity {
    return CatsEntity(null, id, image.url)
}

fun CatsEntity.toRemote(): FavouriteCatImageModel {
    return FavouriteCatImageModel(id.toString().toLong(), CatImageModel(catId.toString(), imageUrl))
}