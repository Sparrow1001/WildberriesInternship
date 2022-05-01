package com.example.wildberriesinternship

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

class MyService : Service() {

    /*
        Данный сервис позволяет прослушивать музыку при сворачивании приложения.

        Когда кнопка "Start Music" будет нажата, сервис начнёт свою работу.
        Так как при повторном нажатии кнопки будет вызываться только метод onCreate -
        музыка продолжит играть, пока не будет нажата кнопка "Stop Music" или пока
        трек не закончится.

        Сервис автоматически удалится системой спустя некоторое время после того, как
        закончит проигрывание музыки
     */

    private lateinit var mPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(applicationContext, "Service created", Toast.LENGTH_SHORT).show()

        mPlayer = MediaPlayer.create(applicationContext, R.raw.lofi)
        mPlayer.isLooping.not()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(applicationContext, "Service running", Toast.LENGTH_SHORT).show()
        mPlayer.start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        throw Exception("")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(applicationContext, "Service deleted", Toast.LENGTH_SHORT).show()
        mPlayer.stop()
    }

}