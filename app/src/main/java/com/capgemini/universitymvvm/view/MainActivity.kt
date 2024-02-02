package com.capgemini.universitymvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.capgemini.universitymvvm.R
import com.capgemini.universitymvvm.model.Student
import com.capgemini.universitymvvm.model.StudentDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupData()
    }

    private fun setupData() {

        CoroutineScope(Dispatchers.Default).launch {
            val stdDao = StudentDatabase.getInstance(this@MainActivity).getDao()

            stdDao.addStudent(Student("john", 1, 80))
            stdDao.addStudent(Student("merry", 2, 50))
            stdDao.addStudent(Student("robert", 3, 40))
            stdDao.addStudent(Student("mark", 4, 90))

            val stdList = stdDao.getAllStudents()
            Log.d("MainActivity", "No of students: ${stdList.size}")

            //stdDao.deleteStudent(stdList[0])
            stdList[0].marks = 100

            stdDao.updateMarks(stdList[0])
        }
    }
}