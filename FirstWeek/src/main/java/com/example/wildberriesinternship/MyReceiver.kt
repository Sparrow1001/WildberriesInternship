package com.example.wildberriesinternship

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    // В метод onReceive поступают намеренья из системы. Здесь я их обработываю и вывожу
    // сообщения на экран

    override fun onReceive(context: Context, intent: Intent) {

        val message = "Broadcast intent detected " + intent.action

        Toast.makeText(context, message, Toast.LENGTH_LONG).show()


    }
}