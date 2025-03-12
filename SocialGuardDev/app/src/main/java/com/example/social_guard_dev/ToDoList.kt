package com.example.social_guard_dev

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.social_guard_dev.databinding.FragmentToDoListBinding


class ToDoList : AppCompatActivity() {
    private lateinit var binding: FragmentToDoListBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentToDoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        binding.newTaskButton.setOnClickListener {
            ToDoBox().show(supportFragmentManager, "newTaskTag")
        }

        taskViewModel.name.observe(this){
            binding.taskName.text = String.format("Task Name: %s", it)
        }
        taskViewModel.desc.observe(this){
            binding.taskDesc.text = String.format("Task Desc: %s", it)
        }
    }



}


