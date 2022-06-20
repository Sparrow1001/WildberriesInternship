package com.example.eighthweekthree.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.eighthweekthree.R
import com.example.eighthweekthree.data.CatsRepository
import com.example.eighthweekthree.data.database.CatsDatabase
import com.example.eighthweekthree.data.network.CatApi
import com.example.eighthweekthree.databinding.ActivityMainBinding
import com.example.eighthweekthree.view.favourite.FavouriteViewModel
import com.example.eighthweekthree.view.favourite.FavouriteViewModelProviderFactory
import com.example.eighthweekthree.view.vote.VoteViewModel
import com.example.eighthweekthree.view.vote.VoteViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var voteViewModel: VoteViewModel
    lateinit var favoriteViewModel: FavouriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.listNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)

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