package com.example.fifthweektwo.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HeroResponse(
    val appearance: Appearance,
    val biography: Biography,
    val id: String,
    val image: Image,
    val name: String,
    val powerstats: Powerstats
) : Serializable

data class Appearance(
    val gender: String,
    @SerializedName("hair-color")
    val hairColor: String,
    val height: List<String>,
    val race: String,
    val weight: List<String>
)

data class Biography(
    val aliases: List<String>,
    val alignment: String,
    @SerializedName("first-appearance")
    val firstAppearance: String,
    @SerializedName("full-name")
    val fullName: String,
    @SerializedName("place-of-birth")
    val placeOfBirth: String
)


data class Image(
    val url: String
)

data class Powerstats(
    val intelligence: String,
    val power: String,
    val speed: String,
    val strength: String
)

