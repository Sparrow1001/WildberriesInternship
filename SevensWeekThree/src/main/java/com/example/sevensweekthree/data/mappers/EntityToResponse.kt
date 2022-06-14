package com.example.sevensweekthree.data.mappers

import com.example.sevensweekthree.data.database.CatsEntity
import com.example.sevensweekthree.data.models.CatImageModel
import com.example.sevensweekthree.data.models.FavouriteCatImageModel

fun FavouriteCatImageModel.toLocal(): CatsEntity{
    return CatsEntity(null, id, image.url)
}

fun CatsEntity.toRemote(): FavouriteCatImageModel{
    return FavouriteCatImageModel(id.toString().toLong(), CatImageModel(catId.toString(), imageUrl))
}