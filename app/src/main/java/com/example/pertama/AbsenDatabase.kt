package com.example.pertama;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase

import kotlin.jvm.Volatile;

@Database(entities = [UserEntity::class], version= 2, exportSchema = false)
abstract class AbsenDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
    companion object {
    @Volatile
    private var INSTANCE: AbsenDatabase? = null

    fun getDatabase(context :Context) : AbsenDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AbsenDatabase::class.java,
                "aplikasiabsen"
            ).addMigrations(MIGRATION_1_2).build()
            INSTANCE = instance
            instance
        }
    }
    }
}
