package com.capgemini.universitymvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    val name: String,
    @PrimaryKey val id: Int,
    var marks: Int,

)