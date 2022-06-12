package com.example.sevensweekone

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sevensweekone.model.local.HeroLocal
import com.example.sevensweekone.model.network.HeroAPI
import com.example.sevensweekone.utils.Resource

class Repository(
    private val heroApi: HeroAPI,
    private val heroLocal: HeroLocal
) {

    fun getHeroes(): List<HeroDTO> {

        val localList = heroLocal.getFromFile()
        Log.d("GGS2", localList.toString())


        return if (localList.isNotEmpty()) {

            localList

        } else {
            val remoteList = getHeroFromApi()
            heroLocal.saveToFile(remoteList)
            Log.d("GGS3", heroLocal.getFromFile().toString())
            heroLocal.getFromFile()

        }


    }

    private fun getHeroFromApi(): List<HeroDTO> {
        return heroApi.getHeroesFromApi()
    }
}