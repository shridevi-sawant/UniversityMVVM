package com.capgemini.universitymvvm.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {

    @Insert
    fun addStudent(std: Student)

    @Delete
    fun deleteStudent(std: Student)

    @Query("delete from student where marks < :m ")
    fun deleteAllStds(m: Int)

    @Update
    fun updateMarks(std: Student)

    @Query("select * from student")
    fun getAllStudents(): List<Student>
}