package com.example.fifthweekthree.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavouriteCatImageModel (
    @SerialName("id")
    val id: Long,
    @SerialName("image")
    val image: CatImageModel
)
