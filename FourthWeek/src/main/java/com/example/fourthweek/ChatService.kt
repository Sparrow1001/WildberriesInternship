package com.example.fourthweek

import com.example.fourthweek.models.ChatData

typealias ChatsListener = (chats: List<ChatData>) -> Unit

class ChatService {

    private var chats = mutableListOf<ChatData>()
    private var homeRepository = HomeRepository()

    private val listeners = mutableSetOf<ChatsListener>()

    init {
        chats = homeRepository.getChats(chats) as MutableList<ChatData>
        notifyChanges()
    }

    fun getChats(){
        chats = ArrayList(chats)
        chats = homeRepository.getChats(chats).toMutableList()
        notifyChanges()
    }

    fun deleteChat(chat: ChatData){
        val indexToDelete = chats.indexOfFirst { it.id == chat.id }
        if (indexToDelete != -1) {
            chats = ArrayList(chats)
            chats.removeAt(indexToDelete)
            notifyChanges()
        }
    }

    fun addListener(listener: ChatsListener){
        listeners.add(listener)
        listener.invoke(chats)
    }

    fun removeListener(listener: ChatsListener){
        listeners.remove(listener)
    }

    private fun notifyChanges(){
        listeners.forEach { it.invoke(chats) }
    }

}