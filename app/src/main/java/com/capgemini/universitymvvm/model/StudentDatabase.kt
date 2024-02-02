package com.capgemini.universitymvvm.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
Singleton

- private constructor
- static instance
 */

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase : RoomDatabase(){

    abstract fun getDao(): StudentDao

    companion object {
        private var instance : StudentDatabase? = null

        fun getInstance(ctx: Context): StudentDatabase {
            return instance ?: buildDB(ctx).also {
                instance = it
            }
        }

        private fun buildDB(ctx: Context): StudentDatabase {
            return Room.databaseBuilder(ctx.applicationContext,
                StudentDatabase::class.java,
                "student.db").build()
        }

    }
}