package com.example.sevensweekthree.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.sevensweekthree.R
import com.example.sevensweekthree.data.CatsRepository
import com.example.sevensweekthree.data.database.CatsDatabase
import com.example.sevensweekthree.data.network.CatApi
import com.example.sevensweekthree.ui.favourite.FavouriteViewModel
import com.example.sevensweekthree.ui.favourite.FavouriteViewModelProviderFactory
import com.example.sevensweekthree.ui.vote.VoteViewModel
import com.example.sevensweekthree.ui.vote.VoteViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    lateinit var voteViewModel: VoteViewModel
    lateinit var favoriteViewModel: FavouriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val catsRepository = CatsRepository(CatApi.create(), CatsDatabase(this))

        val voteViewModelProviderFactory = VoteViewModelProviderFactory(catsRepository)
        val favouriteViewModelProviderFactory = FavouriteViewModelProviderFactory(catsRepository)

        voteViewModel =
            ViewModelProvider(this, voteViewModelProviderFactory)[VoteViewModel::class.java]

        favoriteViewModel = ViewModelProvider(
            this,
            favouriteViewModelProviderFactory
        )[FavouriteViewModel::class.java]

    }
}