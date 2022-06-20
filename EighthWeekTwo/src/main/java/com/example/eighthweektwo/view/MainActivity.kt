package com.example.eighthweektwo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.eighthweektwo.R
import com.example.eighthweektwo.view.home.HeroViewModelProviderFactory
import com.example.eighthweektwo.view.home.HomeViewModel
import com.example.eighthweektwo.data.HeroRepository
import com.example.eighthweektwo.data.local.HeroLocal
import com.example.eighthweektwo.view.home.HomeFragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: HomeViewModel

    private val cicerone = Cicerone.create()
    private val router get() = cicerone.router
    private val navigatorHolder get() = cicerone.getNavigatorHolder()

    private val navigator = AppNavigator(this, R.id.listNavHostFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val heroRepository = HeroRepository(HeroLocal(this))
        val viewModelProviderFactory = HeroViewModelProviderFactory(heroRepository, router)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[HomeViewModel::class.java]

        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(FragmentScreen { HomeFragment() })))
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}