package com.example.sevensweektwo.model

import android.util.Log
import com.example.sevensweektwo.Resource
import com.example.sevensweektwo.model.local.HeroLocal
import com.example.sevensweektwo.model.network.RetrofitInstance
import retrofit2.Response

class HeroRepository(
    private val heroLocal: HeroLocal
) {


    suspend fun getHeroes(): Resource<List<HeroResponse>> {
        val localList = heroLocal.getLocalHeroes()

        return if (localList.isEmpty()) {
            val remoteList = handleSuperHeroResponse(RetrofitInstance.api.getHeroById())
            remoteList.data?.let { heroLocal.saveLocalHeroes(it) }
            Log.d("GGG", remoteList.data.toString())
            Resource.Success(heroLocal.getLocalHeroes())
        } else {
            Log.d("GGG1", localList.toString())
            Resource.Success(localList)
        }
    }

    private fun handleSuperHeroResponse(response: Response<List<HeroResponse>>): Resource<List<HeroResponse>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}