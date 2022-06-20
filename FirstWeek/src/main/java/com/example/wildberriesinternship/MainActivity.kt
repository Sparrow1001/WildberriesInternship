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

    Обработка нажатия на кнопку проходит с помощью метода setOnClickListener, в котором мы запускаем
    новую активность испольуя Intent.


    Кроме того, в этой активности находится "мост для переброски". Для задания с BroadcastReceiver я
    сделал возможность отправить ссылку на активность в приложении. Так как MainActivity открывается первой
    при запуске приложения, я, при получении нужного намеренья, перекидываю пользователя на нужную активность.
     */

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        touchListener()

    }

    private fun touchListener() {
        binding.contentProviderBt.setOnClickListener {
            startActivity(Intent(applicationContext, ProviderExampleActivity::class.java))
        }

        binding.broadcastBt.setOnClickListener {
            startActivity(Intent(applicationContext, BroadcastExampleActivity::class.java))
        }

        binding.serviceBt.setOnClickListener {
            startActivity(Intent(applicationContext, ServiceExampleActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        val income = intent.data
        if (income != null) {
            val intent1 = Intent(this, BroadcastExampleActivity::class.java)
            intent.data = null
            startActivity(intent1)
        }
    }
}