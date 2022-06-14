package com.example.sevensweektwo.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sevensweektwo.data.HeroRepository

class HeroViewModelProviderFactory(
    val heroRepository: HeroRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(heroRepository) as T
    }
}