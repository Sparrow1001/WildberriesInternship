package com.example.sevensweektwo.domain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sevensweektwo.Resource
import com.example.sevensweektwo.model.HeroRepository
import com.example.sevensweektwo.model.HeroResponse
import kotlinx.coroutines.launch
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

            superHeroes.postValue(heroRepository.getHeroes())

        } catch (t: Throwable) {
            when (t) {
                is IOException -> superHeroes.postValue(Resource.Error("Network Failure"))
                else -> superHeroes.postValue(Resource.Error("Conversion Error: ${t.message}"))
            }
        }
    }




}