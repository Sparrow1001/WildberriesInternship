package com.example.sevensweekthree.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavouriteCatModel(
    @SerialName("image_id")
    val image_id: String
)
