package com.example.eighthweekthree.view.vote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eighthweekthree.data.CatsRepository
import com.example.eighthweekthree.data.models.CatImageModel
import com.example.eighthweekthree.data.models.FavouriteCatModel
import com.example.eighthweekthree.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class VoteViewModel(private val catsRepository: CatsRepository) : ViewModel() {

    private val _catImage: MutableLiveData<Resource<CatImageModel>> = MutableLiveData()
    val catImage: LiveData<Resource<CatImageModel>> = _catImage
    private var imageId: String = ""

    init {
        getCatImages()
    }

    fun getCatImages() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = catsRepository.getCatsImagesFromApi()[0]
            imageId = response.id
            handleResult(response)
        }
    }

    fun saveImageInFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            catsRepository.saveImageInFavouritesInApi(FavouriteCatModel(imageId))
        }
    }

    private fun handleResult(response: CatImageModel) {

        try {
            Log.d("GGS", response.toString())
            _catImage.postValue(Resource.Success(response))

        } catch (e: Exception) {
            _catImage.postValue(Resource.Error(e.message.toString()))
        }
    }

}