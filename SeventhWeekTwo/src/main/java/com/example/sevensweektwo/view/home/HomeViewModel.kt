package com.example.sevensweektwo.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevensweektwo.utils.Resource
import com.example.sevensweektwo.data.HeroRepository
import com.example.sevensweektwo.data.HeroResponse
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel(
    private val heroRepository: HeroRepository
) : ViewModel() {

    val superHeroes: MutableLiveData<Resource<List<HeroResponse>>> = MutableLiveData()

    init {
        getSuperHeroes()
    }

    fun getSuperHeroes() = viewModelScope.launch {
        superHeroes.postValue(Resource.Loading())
        try {

            superHeroes.postValue(Resource.Success(heroRepository.getHeroes()))

        } catch (t: Throwable) {
            when (t) {
                is IOException -> {
                    superHeroes.postValue(
                        Resource.NoInternet(
                            "Network Failure",
                            heroRepository.getLocalList()
                        )
                    )
                }
                else -> superHeroes.postValue(Resource.Error("Conversion Error: ${t.message}"))
            }
        }
    }

}