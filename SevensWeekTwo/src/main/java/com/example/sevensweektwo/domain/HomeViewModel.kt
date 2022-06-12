package com.example.sevensweektwo.domain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sevensweektwo.Resource
import com.example.sevensweektwo.model.HeroRepository
import com.example.sevensweektwo.model.HeroResponse
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

            superHeroes.postValue(handleSuperHeroResponse(heroRepository.getHeroById()))

        } catch (t: Throwable) {
            when (t) {
                is IOException -> superHeroes.postValue(Resource.Error("Network Failure"))
                else -> superHeroes.postValue(Resource.Error("Conversion Error: ${t.message}"))
            }
        }
    }

    private fun handleSuperHeroResponse(response: Response<List<HeroResponse>>) : Resource<List<HeroResponse>>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


}