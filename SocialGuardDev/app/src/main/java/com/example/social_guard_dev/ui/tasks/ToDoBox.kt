package com.example.social_guard_dev.ui.tasks

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.social_guard_dev.databinding.FragmentToDoBoxBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalTime

class ToDoBox(var itemBox: ItemBox?) : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentToDoBoxBinding
    private lateinit var taskViewModel: TaskViewModel
    private var dueTime: LocalTime? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(itemBox != null){
            binding.taskTitle.text = "Edit This Task"
            val edit = Editable.Factory.getInstance()
            binding.name.text = edit.newEditable(itemBox!!.name)
            binding.desc.text = edit.newEditable(itemBox!!.desc)
        }
        else{
            binding.taskTitle.text = "New Task"
        }
        taskViewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        binding.saveButton.setOnClickListener {
            saveAction()
        }
        binding.timePickerButton.setOnClickListener {
            openTimePicker()
        }
    }

    private fun openTimePicker() {
        if(dueTime == null)
            dueTime = LocalTime.now()
        val listener = TimePickerDialog.OnTimeSetListener{ _, selectedHour, selectedMinute ->
            dueTime = LocalTime.of(selectedHour, selectedMinute)
            updateTimeButtonText()
        }
        val dialog = TimePickerDialog(activity, listener, dueTime!!.hour, dueTime!!.minute, true)
        dialog.setTitle("Task Due")
        dialog.show()

    }

    private fun updateTimeButtonText() {
        binding.timePickerButton.text = String.format("%02d:%02d",dueTime!!.hour,dueTime!!.minute)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentToDoBoxBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun saveAction(){
        val name = binding.name.text.toString()
        val desc = binding.desc.text.toString()
        if(itemBox == null){
            val newTask = ItemBox(name, desc, null, null)
            taskViewModel.addTaskItem(newTask)
        }
        else{
            taskViewModel.updateTaskItem(itemBox!!.id, name, desc, null)
        }
        binding.name.setText("")
        binding.desc.setText("")
        dismiss()
    }
}