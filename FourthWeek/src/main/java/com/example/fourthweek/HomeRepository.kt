package com.example.fourthweek

import com.example.fourthweek.models.ChatData
import java.util.*
import kotlin.collections.ArrayList

class HomeRepository {

    fun getChats(list: List<ChatData>): List<ChatData> {
        var newList = mutableListOf<ChatData>()
        newList += list
        if (newList.isNotEmpty()) {

            for (i in 0..newList.size - 1){
                val change = Random().nextBoolean()
                if (change){
                    newList = ArrayList(newList)
                    newList[i].apply {
                        lastMessage = getRandomString(10)
                        unreadCounter = Random().nextInt(100)
                    }
                }
            }

            return fillList(newList)

        } else {

            return fillList(newList)

        }
    }

    private fun fillList(list: List<ChatData>): List<ChatData>{
        val randomQuantityRange = 0..Random().nextInt(5)
        var newList = mutableListOf<ChatData>()
        newList += list
        for (i in randomQuantityRange) {
            val chatData = ChatData(
                id = Random().nextInt(999),
                chatName = getRandomString(Random().nextInt(20)),
                dateTime = "${Random().nextInt(2)}${Random().nextInt(3)}:" +
                        "${Random().nextInt(6)}${Random().nextInt(9)}",
                lastMessage = getRandomString(Random().nextInt(20)),
                unreadCounter = Random().nextInt(100)
            )
            newList = ArrayList(newList)
            newList.add(chatData)
        }
        return newList
    }

    private fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

}



