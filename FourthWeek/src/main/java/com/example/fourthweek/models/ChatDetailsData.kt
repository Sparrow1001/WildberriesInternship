package com.example.fourthweek.models

data class ChatDetailsData(
    val id: Int,
    val sendType: Int,
    var message: String,
    val name: String,
    val dateTime: String
)
