package com.example.eighthweekthree.data.database

import androidx.room.*

@Dao
interface CatsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun upsert(cats: CatsEntity)

    @Query("SELECT * FROM cats")
    fun getAllCats(): List<CatsEntity>

}