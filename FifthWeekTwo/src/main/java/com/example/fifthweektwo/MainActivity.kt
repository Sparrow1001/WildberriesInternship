package com.example.fifthweektwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.fifthweektwo.domain.HeroViewModelProviderFactory
import com.example.fifthweektwo.domain.HomeViewModel
import com.example.fifthweektwo.model.HeroRepository


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