package com.example.sevensweektwo.data

import android.util.Log
import com.example.sevensweektwo.utils.Resource
import com.example.sevensweektwo.data.local.HeroLocal
import com.example.sevensweektwo.data.network.RetrofitInstance
import retrofit2.Response

class HeroRepository(
    private val heroLocal: HeroLocal
) {

    suspend fun getHeroes(): List<HeroResponse> {
        val localList = heroLocal.getLocalHeroes()
        val remoteList = handleSuperHeroResponse(RetrofitInstance.api.getHeroById(), localList)


        return if (localList.isEmpty() || localList != remoteList.data) {

            remoteList.data?.let { heroLocal.saveLocalHeroes(it) }
            Log.d("GGG", remoteList.data.toString())
            getLocalList()

        } else {
            Log.d("GGG1", localList.toString())
            localList
        }
    }

    fun getLocalList(): List<HeroResponse> {
        return heroLocal.getLocalHeroes()
    }

    private fun handleSuperHeroResponse(
        response: Response<List<HeroResponse>>,
        localList: List<HeroResponse>
    ): Resource<List<HeroResponse>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        } else {
            return Resource.NoInternet(response.message(), localList)
        }
        return Resource.Error(response.message())
    }

}