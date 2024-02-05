package com.capgemini.universitymvvm.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capgemini.universitymvvm.model.Student
import com.capgemini.universitymvvm.model.StudentRepository

class StudentViewModel(application: Application) : AndroidViewModel(application) {

    // owns repository
    private val repo = StudentRepository(application)
    // hold data required by view(Activity/fragment)
    var stdList = repo.getStudents()
        //MutableLiveData<List<Student>>()
        //listOf<Student>()

    suspend fun addStudent(name: String, id: Int, marks: Int): Boolean {
        return repo.addStudent(name, id, marks)
    }


}