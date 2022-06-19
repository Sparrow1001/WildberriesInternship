package com.example.sevensweektwo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.sevensweektwo.R
import com.example.sevensweektwo.view.home.HeroViewModelProviderFactory
import com.example.sevensweektwo.view.home.HomeViewModel
import com.example.sevensweektwo.data.HeroRepository
import com.example.sevensweektwo.data.local.HeroLocal

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heroRepository = HeroRepository(HeroLocal(this))
        val viewModelProviderFactory = HeroViewModelProviderFactory(heroRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[HomeViewModel::class.java]
    }
}