package com.example.fourthweek.models

import java.util.*

data class ChatData(
    var id: Int,
    var chatName: String,
    var dateTime: String,
    var lastMessage: String,
    var unreadCounter: Int
)