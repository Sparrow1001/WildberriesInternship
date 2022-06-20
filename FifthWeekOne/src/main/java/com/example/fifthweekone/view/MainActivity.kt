package com.example.fifthweekone.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.fifthweekone.view.home.HomeViewModelProviderFactory
import com.example.fifthweekone.R
import com.example.fifthweekone.data.Repository
import com.example.fifthweekone.view.home.HomeViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heroRepository = Repository()
        val viewModelProviderFactory = HomeViewModelProviderFactory(heroRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[HomeViewModel::class.java]
    }
}

