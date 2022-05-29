package com.example.fifthweekone

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HeroViewModelProviderFactory(
    val app: Application,
    val repository: Repository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(app, repository) as T
    }
}