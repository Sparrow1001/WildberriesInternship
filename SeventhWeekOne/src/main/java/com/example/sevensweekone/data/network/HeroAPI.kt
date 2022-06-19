package com.example.sevensweekone.data.network

import android.util.Log
import com.example.sevensweekone.data.HeroDTO
import com.example.sevensweekone.utils.Constants.Companion.BASE_URL
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*

class HeroAPI {

    fun getHeroesFromApi(): List<HeroDTO> {
        val url = "$BASE_URL/api/heroStats"
        val hero = mutableListOf<HeroDTO>()
        val request = Request.Builder().url(url).build()

        try {
            val response = OkHttpClient().newCall(request).execute()
            val json: String? = response.body?.string()
            Log.d("GGS", json.toString())
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
            val list = Types.newParameterizedType(List::class.java, HeroDTO::class.java)
            val adapter: JsonAdapter<List<HeroDTO>> = moshi.adapter(list)
            val data = adapter.fromJson(json)!!
            hero.addAll(data)
        } catch (e: Exception) {
            return emptyList()
        }

        return hero

    }


}