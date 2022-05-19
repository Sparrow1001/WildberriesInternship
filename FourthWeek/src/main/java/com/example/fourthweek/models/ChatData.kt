package com.example.fourthweek.models

import java.util.*

data class ChatData(
    val id: Int,
    val chatName: String,
    val dateTime: String,
    val lastMessage: String,
    val unreadCounter: Int
)