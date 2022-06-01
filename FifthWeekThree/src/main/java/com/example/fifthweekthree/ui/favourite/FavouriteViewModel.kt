package com.example.fifthweekthree.ui.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fifthweekthree.CatsRepository
import com.example.fifthweekthree.models.FavouriteCatImageModel
import com.example.fifthweekthree.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class FavouriteViewModel(val catsRepository: CatsRepository) : ViewModel(){

    val favouriteCats: MutableLiveData<Resource<List<FavouriteCatImageModel>>> = MutableLiveData()

    init {
        getFavouriteCats()
    }

    fun getFavouriteCats(){
        favouriteCats.postValue(Resource.Loading())

        viewModelScope.launch(Dispatchers.IO) {
            val response = catsRepository.getFavouriteCats()
            handleResult(response)
        }
    }

    private fun handleResult(response: List<FavouriteCatImageModel>) {
        try {
            favouriteCats.postValue(Resource.Success(response))

        } catch (e: Exception){
            favouriteCats.postValue(Resource.Error(e.message.toString()))
        }
    }

}