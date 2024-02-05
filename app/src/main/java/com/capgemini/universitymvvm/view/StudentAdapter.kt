package com.capgemini.universitymvvm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.capgemini.universitymvvm.R
import com.capgemini.universitymvvm.model.Student

class StudentAdapter(val stdList: List<Student>): RecyclerView.Adapter<StudentAdapter.StudentHolder>() {

    inner class StudentHolder(inflated: View): ViewHolder(inflated){
        val nameTextView: TextView = inflated.findViewById(R.id.nameT)
        val idTextView: TextView = inflated.findViewById(R.id.rollT)
        val marksTextView: TextView = inflated.findViewById(R.id.marksT)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        // render item xml and return holder
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)

        return StudentHolder(view)
    }

    override fun getItemCount(): Int {
        return stdList.size
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        val std = stdList[position]

        holder.nameTextView.text = std.name
        holder.idTextView.text = "Roll: ${std.id}"
        holder.marksTextView.text = "${std.marks} %"
    }
}