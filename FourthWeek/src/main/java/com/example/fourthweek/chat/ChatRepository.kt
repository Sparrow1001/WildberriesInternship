package com.example.fourthweek.chat

import com.example.fourthweek.models.ChatDetailsData
import java.util.*

class ChatRepository {

    private val nameFrom = getRandomString(20)
    private val nameTo = getRandomString(20)

    fun getChatDetails(list: List<ChatDetailsData>): List<ChatDetailsData> {
        val newList = mutableListOf<ChatDetailsData>()
        newList.addAll(list)

        if (newList.isNotEmpty()) {

            return updateList(newList)

        }

        return fillList(newList)
    }

    fun updateList(list: List<ChatDetailsData>): List<ChatDetailsData> {
        val newList = mutableListOf<ChatDetailsData>()
        newList.addAll(list)

        for (i in 0 until newList.size) {
            val change = Random().nextBoolean()
            if (change) {
                newList[i].apply {
                    message = getRandomString(10)
                }
            }
        }

        return newList
    }

    fun fillList(list: List<ChatDetailsData>): List<ChatDetailsData> {
        val newList = mutableListOf<ChatDetailsData>()

        val sendTypeFrom = 1
        val sendTypeTo = 2
        newList.addAll(list)
        newList.addAll((1..6).map {

            ChatDetailsData(
                id = Random().nextInt(999),
                sendType = sendTypeFrom,
                message = getRandomString((1..100).random()),
                name = nameFrom,
                dateTime = "${Random().nextInt(2)}${Random().nextInt(3)}:" +
                        "${Random().nextInt(6)}${Random().nextInt(9)}"
            )
        })

        newList.addAll((1..5).map {

            ChatDetailsData(
                id = Random().nextInt(999),
                sendType = sendTypeTo,
                message = getRandomString((1..100).random()),
                name = nameTo,
                dateTime = "${Random().nextInt(2)}${Random().nextInt(3)}:" +
                        "${Random().nextInt(6)}${Random().nextInt(9)}"
            )
        })

        newList.shuffle()

        return newList

    }

    private fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

}