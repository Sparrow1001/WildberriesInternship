package com.example.fifthweektwo.domain

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fifthweektwo.HeroApplication
import com.example.fifthweektwo.Resource
import com.example.fifthweektwo.model.HeroRepository
import com.example.fifthweektwo.model.HeroResponse
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class HomeViewModel(
    app: Application,
    val heroRepository: HeroRepository
) : AndroidViewModel(app) {

    val superHeroes: MutableLiveData<Resource<List<HeroResponse>>> = MutableLiveData()

    init {
        getSuperHeroes()
    }

    private fun getSuperHeroes() = viewModelScope.launch {
        superHeroesCall()
    }

    private suspend fun superHeroesCall() {
        superHeroes.postValue(Resource.Loading())
        try {

            val response = mutableListOf<Response<HeroResponse>>()
            for (i in (1..10)) {
                val hero = heroRepository.getHeroById(i.toString())
                response.add(hero)
            }

            superHeroes.postValue(handleSuperHeroResponse(response))

        } catch (t: Throwable) {
            when (t) {
                is IOException -> superHeroes.postValue(Resource.Error("Network Failure"))
                else -> superHeroes.postValue(Resource.Error("Conversion Error: ${t.message}"))
            }
        }
    }

    private fun handleSuperHeroResponse(response: List<Response<HeroResponse>>): Resource<List<HeroResponse>> {
        val heroList = mutableListOf<HeroResponse>()
        for (i in response.indices) {

            if (response[i].isSuccessful) {
                response[i].body()?.let { resultResponse ->
                    heroList.add(resultResponse)
                }
            } else {
                return Resource.Error(response[i].message())
            }

        }
        return Resource.Success(heroList)
    }



}