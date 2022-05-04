package com.example.secondweek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //Жизненный цикл активности

    //При запуске приложения: onCreate(), onStart(), onResume()
    //При нажатии книпки домой: onPause(), onStop()
    //При нажатии кнопки назад: onPause(), onStop(), onDestroy()
    //При открытии свёрнутого приложения: onRestart(), onStart(), onResume()
    //При повороте экрана: onPause(), onStop(), onDestroy(), onCreate(), onStart(), onResume()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("onCreate", "Activity created")
        Toast.makeText(this, "Activity created", Toast.LENGTH_SHORT).show()
        val button: Button = findViewById(R.id.moveBt)

        button.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("onStart", "Activity started")
        Toast.makeText(this, "Activity started", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("onRestart", "Activity restarted")
        Toast.makeText(this, "Activity restarted", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume", "Activity resumed")
        Toast.makeText(this, "Activity resumed", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Log.d("onPause", "Activity paused")
        Toast.makeText(this, "Activity paused", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Log.d("onStop", "Activity stopped")
        Toast.makeText(this, "Activity stopped", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "Activity destroyed")
        Toast.makeText(this, "Activity destroyed", Toast.LENGTH_SHORT).show()
    }
}