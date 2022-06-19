package com.example.sevensweekthree.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatImageModel(
    @SerialName("id")
    val id: String,
    @SerialName("url")
    val url: String
)
