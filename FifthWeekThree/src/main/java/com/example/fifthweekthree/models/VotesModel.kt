package com.example.fifthweekthree.models

import kotlinx.serialization.Serializable

@Serializable
data class VotesModel(
    val value: Int,
    val image_id: String,
    val sub_id: String,
    val created_at: String,
    val id: String
)
