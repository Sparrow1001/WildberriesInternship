package com.example.sevensweekthree.ui.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.sevensweekthree.data.CatsRepository
import com.example.sevensweekthree.data.database.CatsEntity
import com.example.sevensweekthree.data.models.FavouriteCatImageModel
import com.example.sevensweekthree.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class FavouriteViewModel(val catsRepository: CatsRepository) : ViewModel() {

    val favouriteCats: MutableLiveData<Resource<List<FavouriteCatImageModel>>> = MutableLiveData()

    init {
        getFavouriteCats()
    }

    fun getFavouriteCats() {
        favouriteCats.postValue(Resource.Loading())

        viewModelScope.launch(Dispatchers.IO) {
            val response = catsRepository.getFavouriteCats()
            handleResult(response)
        }
    }

    private suspend fun handleResult(response: List<FavouriteCatImageModel>) {
        try {
            if (catsRepository.getSavedCats().value?.equals(null) == true){
                favouriteCats.postValue(Resource.Success(response))
            } else {
                for (i in response.indices){
                    catsRepository.upsert(CatsEntity(null, response[i].id, response[i].image.url))
                }
               //zz favouriteCats.postValue(catsRepository.getSavedCats().map { FavouriteCatImageModel() })

            }
            favouriteCats.postValue(Resource.Success(response))

        } catch (e: Exception) {
            favouriteCats.postValue(Resource.Error(e.message.toString()))
        }
    }

}