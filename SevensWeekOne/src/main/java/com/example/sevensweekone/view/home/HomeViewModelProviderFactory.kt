package com.example.sevensweekone.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sevensweekone.data.Repository

class HomeViewModelProviderFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}