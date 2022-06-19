package com.example.eighthweekthree.data.models

import com.example.eighthweekthree.data.models.CatImageModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavouriteCatImageModel (
    @SerialName("id")
    val id: Long,
    @SerialName("image")
    val image: CatImageModel
)
