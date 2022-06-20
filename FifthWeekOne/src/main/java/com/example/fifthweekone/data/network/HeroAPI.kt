package com.example.fifthweekone.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.fifthweekone.data.HeroDTO
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import java.io.IOException

class HeroAPI {

    fun getHeroesFromApi(): LiveData<List<HeroDTO>> {
        val url = "https://api.opendota.com/api/heroStats"
        val hero: MutableLiveData<List<HeroDTO>> = MutableLiveData()
        val request = Request.Builder().url(url).build()
        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful) {

                    val json: String? = response.body?.string()
                    Log.d("GGS", json.toString())
                    val moshi = Moshi.Builder()
                        .addLast(KotlinJsonAdapterFactory())
                        .build()
                    val list = Types.newParameterizedType(List::class.java, HeroDTO::class.java)
                    val adapter: JsonAdapter<List<HeroDTO>> = moshi.adapter(list)
                    val data = adapter.fromJson(json)!!
                    hero.postValue(data)
                } else throw IOException(response.message)

            }

        })

        return hero

    }


}