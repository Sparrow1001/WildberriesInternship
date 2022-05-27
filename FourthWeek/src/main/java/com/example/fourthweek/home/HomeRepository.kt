package com.example.fourthweek.home

import com.example.fourthweek.models.ChatData
import java.util.*
import kotlin.collections.ArrayList

class HomeRepository {

    fun getChats(list: List<ChatData>): MutableList<ChatData> {
        val newList = mutableListOf<ChatData>()
        newList.addAll(list)

            return fillList(newList)

    }

    fun updateChats(list: List<ChatData>): MutableList<ChatData>{
        val newList = mutableListOf<ChatData>()
        newList.addAll(list)
        if (newList.isNotEmpty()) {

            for (i in 0..newList.size - 1) {
                val change = Random().nextBoolean()
                if (change) {
                    newList[i].apply {
                        lastMessage = getRandomString(10)
                        unreadCounter = Random().nextInt(100)
                    }
                }
            }

        }
        return newList
    }

    private fun fillList(newList: MutableList<ChatData>): MutableList<ChatData> {

        newList.addAll((0..10).map {
            ChatData(
                id = Random().nextInt(999),
                chatName = getRandomString((1..20).random()),
                dateTime = "${Random().nextInt(2)}${Random().nextInt(3)}:" +
                        "${Random().nextInt(6)}${Random().nextInt(9)}",
                lastMessage = getRandomString((1..40).random()),
                unreadCounter = Random().nextInt(100)
            )
        }.toMutableList())

        return newList
    }

    private fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

}



