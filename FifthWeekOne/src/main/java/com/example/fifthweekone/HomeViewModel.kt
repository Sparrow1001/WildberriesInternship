package com.example.fifthweekone

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class HomeViewModel(
    app: Application,
    repository: Repository
) : AndroidViewModel(app) {

    val hero: MutableLiveData<Resource<List<HeroDTO>>> = MutableLiveData()

    init {
        getHeroes()
    }

    fun getHeroes() {
        TODO("Not yet implemented")
    }

}