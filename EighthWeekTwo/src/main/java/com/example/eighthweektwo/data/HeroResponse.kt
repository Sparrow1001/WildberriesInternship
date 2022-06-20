package com.example.eighthweektwo.data

import java.io.Serializable

data class HeroResponse(
    val appearance: Appearance,
    val biography: Biography,
    val id: String,
    val images: Images,
    val name: String,
    val powerstats: Powerstats
) : Serializable

data class Appearance(
    val gender: String,
    val hairColor: String,
    val height: List<String>,
    val weight: List<String>
)

data class Biography(
    val aliases: List<String>,
    val alignment: String,
    val firstAppearance: String,
    val fullName: String,
    val placeOfBirth: String
)


data class Images(
    val sm: String,
    val md: String
)

data class Powerstats(
    val intelligence: String,
    val power: String,
    val speed: String,
    val strength: String
)

