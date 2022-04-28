package com.example.wildberriesinternship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wildberriesinternship.databinding.ActivityProviderExampleBinding

class ProviderExampleActivity : AppCompatActivity() {

    lateinit var binding: ActivityProviderExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProviderExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}