package com.example.eighthweektwo.data

import android.util.Log
import com.example.eighthweektwo.utils.Resource
import com.example.eighthweektwo.data.local.HeroLocal
import com.example.eighthweektwo.data.network.RetrofitInstance
import retrofit2.Response

class HeroRepository(
    private val heroLocal: HeroLocal
) {


    suspend fun getHeroes(): Resource<List<HeroResponse>> {
        val localList = heroLocal.getLocalHeroes()
        val remoteList = handleSuperHeroResponse(RetrofitInstance.api.getHeroById())


        return if (localList.isEmpty() || localList != remoteList.data) {

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