package com.example.eighthweektwo.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eighthweektwo.data.HeroRepository
import com.github.terrakok.cicerone.Router

class HeroViewModelProviderFactory(
    private val heroRepository: HeroRepository,
    private val router: Router
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(heroRepository, router) as T
    }
}