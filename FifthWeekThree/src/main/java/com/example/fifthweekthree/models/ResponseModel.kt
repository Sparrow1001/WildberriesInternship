package com.example.fifthweekthree.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseModel(
    @SerialName("id")
    val id: Long,
    @SerialName("message")
    val message: String
)