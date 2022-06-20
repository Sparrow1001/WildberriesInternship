package com.example.fifthweektwo.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fifthweektwo.utils.Resource
import com.example.fifthweektwo.data.HeroRepository
import com.example.fifthweektwo.data.HeroResponse
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class HomeViewModel(
    private val heroRepository: HeroRepository
) : ViewModel() {

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

            val hero = heroRepository.getHeroById()
            superHeroes.postValue(handleSuperHeroResponse(hero))

        } catch (t: Throwable) {
            when (t) {
                is IOException -> superHeroes.postValue(Resource.Error("Network Failure"))
                else -> superHeroes.postValue(Resource.Error("Conversion Error: ${t.message}"))
            }
        }
    }

    private fun handleSuperHeroResponse(response: Response<List<HeroResponse>>): Resource<List<HeroResponse>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


}