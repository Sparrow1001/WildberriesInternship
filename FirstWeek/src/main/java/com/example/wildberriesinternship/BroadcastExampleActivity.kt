package com.example.wildberriesinternship

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wildberriesinternship.databinding.ActivityBroadcastExampleBinding

/*
Broadcast Receiver - это приёмник широковещательных сообщений. Мы можем отправить из своего приложения
сообщение, и любая программа, имеющая специальный приёмник сможет его поймать и взаимодействовать с ним.

Существует множество системных сообщений, которые программа может принимать. Это может быть вкл\выкл провод
зарядки, подключения к интернету, wifi или авиарежима.

Кроме того, можно создать собственные сообщения, например сделать собственную ссылку на своё приложение, как сделал я.
После нажатия на такую ссылку, система пробежится по манифестам установленных приложений и найдёт
нужную схему, после чего предложит открыть это приложение.

Кроме того, я реализовал отображение сообщения при отключении зарядного кабеля.

Примером можно назвать любое приложение, которое имеет собственную ссылку. (VK, Yandex_Maps)

 */

class BroadcastExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBroadcastExampleBinding

    private lateinit var receiver: MyReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBroadcastExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receiver = MyReceiver()
        IntentFilter(Intent.ACTION_POWER_DISCONNECTED).also {
            registerReceiver(receiver, it)
        }

        binding.sendMsBt.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, "https://ru.wild_app/")
            intent.type = "text/plain"

            val shareIntent = Intent.createChooser(intent, null)
            startActivity(shareIntent)
        }

    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }


}