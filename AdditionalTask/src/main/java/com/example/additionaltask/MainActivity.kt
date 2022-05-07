package com.example.additionaltask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val c = intArrayOf(4, 1, 8, 2, 6, 0, 2, 5, 9, 10, 14, 1, 7)
        val a = BogoSort()
        println(c.contentToString())
        a.bogosort(c)
        println(c.contentToString())
    }
}