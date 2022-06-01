package com.example.fifthweekthree.ui.vote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fifthweekthree.CatsRepository
import com.example.fifthweekthree.models.CatImageModel
import com.example.fifthweekthree.models.FavouriteCatModel
import com.example.fifthweekthree.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class VoteViewModel(val catsRepository: CatsRepository) : ViewModel() {

    private val _catImage: MutableLiveData<Resource<CatImageModel>> = MutableLiveData()
    val catImage: LiveData<Resource<CatImageModel>> = _catImage
    private var imageId: String = ""

    init {
        getCatImages()
    }

    fun getCatImages() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = catsRepository.getCatsImages()[0]
            imageId = response.id
            handleResult(response)
        }
    }

    fun saveImageInFavorites(){
        viewModelScope.launch(Dispatchers.IO) {
            catsRepository.saveImageInFavourites(FavouriteCatModel(imageId))
        }
    }

    private fun handleResult(response: CatImageModel) {

        try {
            _catImage.postValue(Resource.Success(response))

        } catch (e: Exception){
            _catImage.postValue(Resource.Error(e.message.toString()))
        }
    }

}