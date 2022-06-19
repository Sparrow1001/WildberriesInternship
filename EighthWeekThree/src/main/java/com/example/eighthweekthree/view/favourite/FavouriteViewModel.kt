package com.example.eighthweekthree.view.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eighthweekthree.data.CatsRepository
import com.example.eighthweekthree.data.models.FavouriteCatImageModel
import com.example.eighthweekthree.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class FavouriteViewModel(private val catsRepository: CatsRepository) : ViewModel() {

    val favouriteCats: MutableLiveData<Resource<List<FavouriteCatImageModel>>> = MutableLiveData()

    init {
        getFavouriteCats()
    }

    fun getFavouriteCats() {
        favouriteCats.postValue(Resource.Loading())

        viewModelScope.launch(Dispatchers.IO) {
            val response = catsRepository.getFavouritesCats()
            handleResult(response)
        }
    }

    private fun handleResult(response: List<FavouriteCatImageModel>) {
        try {

            favouriteCats.postValue(Resource.Success(response))

        } catch (e: Exception) {
            favouriteCats.postValue(Resource.Error(e.message.toString()))
        }
    }

}