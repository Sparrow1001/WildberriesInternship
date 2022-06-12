package com.example.sevensweekone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.sevensweekone.model.local.HeroLocal
import com.example.sevensweekone.model.network.HeroAPI
import com.example.sevensweekone.view.home.HomeViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heroRepository = Repository(HeroAPI(), HeroLocal(applicationContext))
        val viewModelProviderFactory = HeroViewModelProviderFactory(application, heroRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[HomeViewModel::class.java]
    }
}