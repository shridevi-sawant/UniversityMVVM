package com.capgemini.universitymvvm.model

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class StudentRepository(val ctx: Context) {

    val stdDao = StudentDatabase.getInstance(ctx).getDao()

    suspend fun addStudent(name: String, roll: Int, marks: Int ): Boolean{
        var isAdded = false
        try {
            stdDao.addStudent(Student(name, roll, marks))
            isAdded = true
        } catch (err: Exception) {
            isAdded = false
        }
        return isAdded
    }

    fun getStudents(): LiveData<List<Student>> {

            return stdDao.getAllStudents()

    }

    suspend fun deleteStudent(stdToDelete: Student) {
        stdDao.deleteStudent(stdToDelete)
    }
}