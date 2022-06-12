package com.example.sevensweekone.model.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import coil.network.HttpException
import com.example.sevensweekone.HeroDTO
import com.example.sevensweekone.utils.Resource
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import java.io.IOException

class HeroAPI {

    fun getHeroesFromApi(): List<HeroDTO> {
        val url = "https://api.opendota.com/api/heroStats"
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
        } catch (e: Exception){
            return emptyList()
        }

        return hero

    }


}