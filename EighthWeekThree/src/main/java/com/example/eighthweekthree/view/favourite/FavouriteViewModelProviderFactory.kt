package com.example.eighthweekthree.view.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eighthweekthree.data.CatsRepository

class FavouriteViewModelProviderFactory (
    private val catsRepository: CatsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavouriteViewModel(catsRepository) as T
    }
}