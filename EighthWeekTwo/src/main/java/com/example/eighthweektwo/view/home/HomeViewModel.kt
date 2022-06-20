package com.example.eighthweektwo.view.home

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eighthweektwo.utils.Resource
import com.example.eighthweektwo.data.HeroRepository
import com.example.eighthweektwo.data.HeroResponse
import com.example.eighthweektwo.view.about.AboutAppFragment
import com.example.eighthweektwo.view.details.DetailsFragment
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel(
    private val heroRepository: HeroRepository,
    private val router: Router
) : ViewModel() {

    val superHeroes: MutableLiveData<Resource<List<HeroResponse>>> = MutableLiveData()

    init {
        getSuperHeroes()
    }

    fun getSuperHeroes() = viewModelScope.launch {
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

    fun openDetailsFragment(hero: Bundle) {
        router.navigateTo(FragmentScreen{DetailsFragment().apply { arguments = hero }})
    }

    fun openAboutFragment(){
        router.navigateTo(FragmentScreen{AboutAppFragment()})
    }


}