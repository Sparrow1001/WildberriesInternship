package com.example.eightsweekone.data

import android.util.Log
import com.example.eightsweekone.data.local.HeroLocal
import com.example.eightsweekone.data.network.HeroAPI

class HeroRepository(
    private val heroApi: HeroAPI,
    private val heroLocal: HeroLocal
) {

    fun getHeroes(): List<HeroDTO> {

        val localList = heroLocal.getFromFile()
        val remoteList = getHeroFromApi()
        Log.d("GGS2", localList.toString())


        return if (localList == remoteList || remoteList.isEmpty()) {

            localList

        }
        else {

            heroLocal.saveToFile(remoteList)
            Log.d("GGS3", heroLocal.getFromFile().toString())
            heroLocal.getFromFile()

        }
    }

    private fun getHeroFromApi(): List<HeroDTO> {
        return heroApi.getHeroesFromApi()
    }

}