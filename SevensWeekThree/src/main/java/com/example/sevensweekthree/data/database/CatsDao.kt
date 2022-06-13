package com.example.sevensweekthree.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CatsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun upsert(cats: CatsEntity)

    @Query("SELECT * FROM cats")
    fun  getAllCats(): LiveData<List<CatsEntity>>

}