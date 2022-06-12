package com.example.sevensweekone.model.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.example.sevensweekone.HeroDTO
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class HeroLocal(private val context: Context) {

    private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val list = Types.newParameterizedType(List::class.java, HeroDTO::class.java)
    private val adapter: JsonAdapter<List<HeroDTO>> = moshi.adapter(list)

    fun saveToFile(heroes: List<HeroDTO>): Boolean {
        val heroesJson = adapter.toJson(heroes)

        return try {
            val fileOutputStream = context.openFileOutput("heroes.txt", MODE_PRIVATE)
            fileOutputStream.write(heroesJson.toByteArray())
            fileOutputStream.close()
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getFromFile(): List<HeroDTO> {
        return try {

            val fileInputStream = context.openFileInput("heroes.txt")
            val bytes = ByteArray(fileInputStream.available())
            fileInputStream.read(bytes)
            val heroesJson = String(bytes)
            fileInputStream?.close()
            Log.d("GGS1", adapter.fromJson(heroesJson).toString())
            adapter.fromJson(heroesJson) ?: emptyList()

        } catch (e: Exception) {

            e.message?.let { Log.e("JsError", it) }
            emptyList()
        }
    }
}