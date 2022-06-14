package com.example.sevensweekone.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.sevensweekone.R
import com.example.sevensweekone.data.HeroRepository
import com.example.sevensweekone.data.local.HeroLocal
import com.example.sevensweekone.data.network.HeroAPI
import com.example.sevensweekone.view.home.HomeViewModelProviderFactory
import com.example.sevensweekone.view.home.HomeViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heroRepository = HeroRepository(HeroAPI(), HeroLocal(this))
        val viewModelProviderFactory = HomeViewModelProviderFactory(heroRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[HomeViewModel::class.java]
    }
}