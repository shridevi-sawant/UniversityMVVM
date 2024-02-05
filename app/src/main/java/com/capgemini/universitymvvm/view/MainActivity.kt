package com.capgemini.universitymvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capgemini.universitymvvm.R
import com.capgemini.universitymvvm.model.Student
import com.capgemini.universitymvvm.model.StudentDatabase
import com.capgemini.universitymvvm.model.StudentRepository
import com.capgemini.universitymvvm.viewModel.StudentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    // owns viewModel
    lateinit var studentVM: StudentViewModel

    lateinit var rView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rView = findViewById(R.id.rView)
        rView.layoutManager = LinearLayoutManager(this)

        val touchHelper = ItemTouchHelper(MySwipeCallback())
        touchHelper.attachToRecyclerView(rView)

        // get VM from view model store
        studentVM = ViewModelProvider(this).get(StudentViewModel::class.java)
            //StudentViewModel(application) // DON't do this

        studentVM.stdList.observe(this){
            // executed as and when data is changed
            // and activity is in active state
            Log.d("MainActivity", "list observer called")
            rView.adapter = StudentAdapter(it)
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_add -> {
                val intent = Intent(this, AddStudentActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class MySwipeCallback: ItemTouchHelper.SimpleCallback(0,
        ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {

            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            studentVM.deleteStudent(viewHolder.adapterPosition)
        }

    }
}