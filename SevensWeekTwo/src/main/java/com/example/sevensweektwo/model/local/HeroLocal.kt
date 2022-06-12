package com.example.sevensweektwo.model.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.sevensweektwo.model.HeroResponse
import com.google.gson.GsonBuilder

class HeroLocal(context: Context) {

    private val sharedPref = context.getSharedPreferences("heroes_storage", MODE_PRIVATE)
    private val gson = GsonBuilder().create()

    fun saveLocalHeroes(heroesList: List<HeroResponse>) {

        val heroesListJson = gson.toJson(heroesList)
        val editor = sharedPref.edit()
        editor.putString("heroes_storage", heroesListJson)
        editor.apply()

    }

    fun getLocalHeroes(): List<HeroResponse> {
        val heroListJson = sharedPref.getString("heroes_storage", "")

        return if (heroListJson == "") {
            emptyList()
        } else {
            gson.fromJson(heroListJson, Array<HeroResponse>::class.java).asList()
        }
    }

}