package com.example.secondweek

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import java.util.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        Log.d("onCreate", "ActivitySecond created")
        Toast.makeText(this, "ActivitySecond created", Toast.LENGTH_SHORT).show()

        val button: Button = findViewById(R.id.changeBt)
        val layout: LinearLayout = findViewById(R.id.layout)

        button.setOnClickListener {
            showDialog(layout)
        }

    }

    @SuppressLint("ResourceAsColor")
    private fun showDialog(layout: LinearLayout) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Change background color")
        builder.setMessage("Do you want change background color?")

        builder.setPositiveButton("yes"){dialog, which ->

            val rnd = Random()
            val color = Color.argb(255,
                rnd.nextInt(256),
                rnd.nextInt(256),
                rnd.nextInt(256))

            layout.setBackgroundColor(color)
        }

        builder.setNegativeButton("no"){dialog, which ->
            Toast.makeText(this, "Okay :(", Toast.LENGTH_SHORT).show()
        }

        builder.show()
    }

    override fun onStart() {
        super.onStart()
        Log.d("onStart", "ActivitySecond started")
        Toast.makeText(this, "ActivitySecond started", Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("onRestart", "ActivitySecond restarted")
        Toast.makeText(this, "ActivitySecond restarted", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume", "ActivitySecond resumed")
        Toast.makeText(this, "ActivitySecond resumed", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Log.d("onPause", "ActivitySecond paused")
        Toast.makeText(this, "ActivitySecond paused", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Log.d("onStop", "ActivitySecond stopped")
        Toast.makeText(this, "ActivitySecond stopped", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "ActivitySecond destroyed")
        Toast.makeText(this, "ActivitySecond destroyed", Toast.LENGTH_SHORT).show()
    }
}