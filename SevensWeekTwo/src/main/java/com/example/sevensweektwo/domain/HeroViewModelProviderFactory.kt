package com.example.sevensweektwo.domain

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sevensweektwo.model.HeroRepository

class HeroViewModelProviderFactory(
    val app: Application,
    val heroRepository: HeroRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(app, heroRepository) as T
    }
}