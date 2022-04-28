package com.example.wildberriesinternship

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.wildberriesinternship.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /*
    Activity представляет из себя интерфейс, который пользователь видит у себя на экране и с которым
    он может взаимодействовать.

    На данном экране имеются 3 кнопки, позволяющие преходить к другим активностям с примерами основных
    компонентов Android приложениия.
     */

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.contentProviderBt.setOnClickListener{
            startActivity(Intent(applicationContext, ProviderExampleActivity::class.java))
        }


    }
}