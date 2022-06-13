package com.example.sevensweekthree.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CatsEntity::class],
    version = 1
)
abstract class CatsDatabase: RoomDatabase() {

    abstract fun getCatsDao(): CatsDao

    companion object{
        @Volatile
        private var instance: CatsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CatsDatabase::class.java,
                "cats_database.db"
            ).build()
    }

}