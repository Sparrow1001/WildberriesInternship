package com.example.sevensweekthree.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sevensweekthree.data.CatsRepository

class FavouriteViewModelProviderFactory (
    val catsRepository: CatsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavouriteViewModel(catsRepository) as T
    }
}