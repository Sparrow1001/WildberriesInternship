package com.example.sevensweekthree.view.vote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sevensweekthree.data.CatsRepository

class VoteViewModelProviderFactory (
    val catsRepository: CatsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VoteViewModel(catsRepository) as T
    }
}