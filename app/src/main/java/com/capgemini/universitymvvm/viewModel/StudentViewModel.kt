package com.capgemini.universitymvvm.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capgemini.universitymvvm.model.Student
import com.capgemini.universitymvvm.model.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {

    // owns repository
    private val repo = StudentRepository(application)
    // hold data required by view(Activity/fragment)
    var stdList = repo.getStudents()
        //MutableLiveData<List<Student>>()
        //listOf<Student>()

    var isStudentAdded = MutableLiveData<Boolean>(false)

    fun addStudent(name: String, id: Int, marks: Int) {
        // launch coroutine
         viewModelScope.launch(Dispatchers.Default) {
             isStudentAdded.postValue(repo.addStudent(name, id, marks))
         }
    }

    fun deleteStudent(position: Int) {

        val stdToDelete = stdList.value!!.get(position)
        viewModelScope.launch(Dispatchers.Default) {
            repo.deleteStudent(stdToDelete)
        }
    }


}