package com.capgemini.universitymvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.capgemini.universitymvvm.R
import com.capgemini.universitymvvm.model.Student
import com.capgemini.universitymvvm.model.StudentDatabase
import com.capgemini.universitymvvm.model.StudentRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddStudentActivity : AppCompatActivity() {
    lateinit var nameEditText: EditText
    lateinit var idEditText: EditText
    lateinit var marksEditText: EditText

    lateinit var repository: StudentRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        nameEditText = findViewById(R.id.nameE)
        idEditText = findViewById(R.id.rollE)
        marksEditText = findViewById(R.id.marksE)

        repository = StudentRepository(this)
    }

    fun btnClick(view: View) {
        val name = nameEditText.text.toString()
        val rollNo = idEditText.text.toString()
        val marks = marksEditText.text.toString()

        if (name.isNotEmpty() && rollNo.isNotEmpty() && marks.isNotEmpty()){
           CoroutineScope(Dispatchers.Default).launch {
               val result = repository.addStudent(name,
                   rollNo.toInt(), marks.toInt())

               CoroutineScope(Dispatchers.Main).launch {
                   if (result) {
                       Toast.makeText(
                           this@AddStudentActivity,
                           "Student added", Toast.LENGTH_LONG
                       ).show()

                       finish()
                   }
                   else
                       Toast.makeText(this@AddStudentActivity,
                           "Error: Roll number exists already", Toast.LENGTH_LONG).show()
               }
           }
        }
        else {
            Snackbar.make(view, "Pls enter all fields..", 5000).show()
        }
    }
}