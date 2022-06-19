package com.example.eighthweekthree.view.vote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eighthweekthree.data.CatsRepository

class VoteViewModelProviderFactory (
    private val catsRepository: CatsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VoteViewModel(catsRepository) as T
    }
}