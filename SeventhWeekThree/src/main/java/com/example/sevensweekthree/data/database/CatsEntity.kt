package com.example.sevensweekthree.data.database

import androidx.room.*

@Entity(
    tableName = "cats",
    indices = [Index(value = ["catId"], unique = true)]
)
data class CatsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val catId: Long,
    val imageUrl: String
)