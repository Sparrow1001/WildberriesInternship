package com.example.sevensweektwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.sevensweektwo.domain.HeroViewModelProviderFactory
import com.example.sevensweektwo.domain.HomeViewModel
import com.example.sevensweektwo.model.HeroRepository

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heroRepository = HeroRepository()
        val viewModelProviderFactory = HeroViewModelProviderFactory(application, heroRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[HomeViewModel::class.java]
    }
}