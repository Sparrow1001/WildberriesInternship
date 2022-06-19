package com.example.eightsweekone.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eightsweekone.data.HeroRepository

class HomeViewModelProviderFactory(
    private val heroRepository: HeroRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(heroRepository) as T
    }
}