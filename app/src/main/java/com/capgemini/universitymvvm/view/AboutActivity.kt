package com.capgemini.universitymvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.capgemini.universitymvvm.R
import com.capgemini.universitymvvm.databinding.ActivityAboutBinding
import com.capgemini.universitymvvm.model.University
import com.capgemini.universitymvvm.viewModel.StudentViewModel

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityAboutBinding>(this,
            R.layout.activity_about)

        binding.univ = University("IIT Roorkee",
            "Roorkee, Uttarakhand, India",
            "https://www.iitr.com")

        binding.stdVM = ViewModelProvider(this)
            .get(StudentViewModel::class.java)

        binding.lifecycleOwner = this // for liveData
    }
}