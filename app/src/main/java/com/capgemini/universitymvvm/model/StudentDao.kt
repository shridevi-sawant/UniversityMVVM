package com.capgemini.universitymvvm.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {

    @Insert
    suspend fun addStudent(std: Student)

    @Delete
    suspend fun deleteStudent(std: Student)

    @Query("delete from student where marks < :m ")
    suspend fun deleteAllStds(m: Int)

    @Update
    suspend fun updateMarks(std: Student)

    @Query("select * from student")
    fun getAllStudents(): LiveData<List<Student>>
}