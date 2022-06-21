package com.example.fifthweekthree.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.fifthweekthree.data.CatsRepository
import com.example.fifthweekthree.data.network.CatApi
import com.example.fifthweekthree.databinding.ActivityMainBinding
import com.example.fifthweekthree.view.vote.VoteViewModel
import com.example.fifthweekthree.view.vote.VoteViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var voteViewModel: VoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val catsRepository = CatsRepository(CatApi.create())

        val voteViewModelProviderFactory = VoteViewModelProviderFactory(catsRepository)

        voteViewModel =
            ViewModelProvider(this, voteViewModelProviderFactory)[VoteViewModel::class.java]
    }
}