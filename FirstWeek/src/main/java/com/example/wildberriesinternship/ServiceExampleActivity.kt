package com.example.wildberriesinternship

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.example.wildberriesinternship.databinding.ActivityServiceExampleBinding

class ServiceExampleActivity : AppCompatActivity() {

    /*
    Service - это один из основных компонентов приложения, который нужен для выполнения
    долгосрочных задач без участия пользователя. Его жизненный цикл не зависит от жзненного цикла
    самого приложения. Сервисы будут работать до тех пор, пока мы их не завершим или пока есть
    свободные системные ресурсы.

    Кроме того, для каких либо трудоёмких задач необходимо создавать новый поток, так как
    сервис использует основной поток по умолчанию, что может вызвать остановку приложения при реализации
    тяжёлой задачи.

    Яркими примерами использования сервисов являются приложения для прослушивания музыки (Яндекс.Музыка,
    Spotify и т.д). Работающий в фоновом режиме сервис позволяет нам продолжать слушать музыку при
    сворачивании приожения или блокировке экрана.
     */

    lateinit var binding: ActivityServiceExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startMcBt.setOnClickListener {
            startService(Intent(this, MyService::class.java))
        }

        binding.stopMcBt.setOnClickListener {
            stopService(Intent(this, MyService::class.java))
        }

    }
}