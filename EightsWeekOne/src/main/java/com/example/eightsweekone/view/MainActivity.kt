package com.example.eightsweekone.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.eightsweekone.R
import com.example.eightsweekone.data.HeroRepository
import com.example.eightsweekone.data.local.HeroLocal
import com.example.eightsweekone.data.network.HeroAPI
import com.example.eightsweekone.view.home.HomeFragment
import com.example.eightsweekone.view.home.HomeViewModelProviderFactory
import com.example.eightsweekone.view.home.HomeViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.listNavHostFragment, HomeFragment()).commit()
        }

        val heroRepository = HeroRepository(HeroAPI(), HeroLocal(this))
        val viewModelProviderFactory = HomeViewModelProviderFactory(heroRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[HomeViewModel::class.java]
    }
}