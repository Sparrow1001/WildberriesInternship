package com.example.sevensweekone.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sevensweekone.data.HeroRepository

class HomeViewModelProviderFactory(
    private val heroRepository: HeroRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(heroRepository) as T
    }
}