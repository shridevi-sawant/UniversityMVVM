package com.capgemini.universitymvvm.model

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class StudentRepository(val ctx: Context) {

    val stdDao = StudentDatabase.getInstance(ctx).getDao()

    suspend fun addStudent(name: String, roll: Int, marks: Int ): Boolean{
        var isAdded = false
        val job = CoroutineScope(Dispatchers.Default).launch {
            try {
                stdDao.addStudent(Student(name, roll, marks))
                isAdded = true
            }catch (err: Exception){
                isAdded = false

            }
        }

        job.join()
        return isAdded
    }

    suspend fun getStudents(): List<Student> {

            val stdListDef = CoroutineScope(Dispatchers.Default).async {
                stdDao.getAllStudents()
            }

            return stdListDef.await()

    }
}