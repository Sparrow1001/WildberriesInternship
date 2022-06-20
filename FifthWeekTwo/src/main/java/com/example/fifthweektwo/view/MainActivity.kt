package com.example.fifthweektwo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.fifthweektwo.R
import com.example.fifthweektwo.view.home.HomeViewModelProviderFactory
import com.example.fifthweektwo.view.home.HomeViewModel
import com.example.fifthweektwo.data.HeroRepository


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heroRepository = HeroRepository()
        val viewModelProviderFactory = HomeViewModelProviderFactory(heroRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[HomeViewModel::class.java]
    }
}