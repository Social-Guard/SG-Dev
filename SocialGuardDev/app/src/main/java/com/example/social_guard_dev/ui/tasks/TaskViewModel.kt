package com.example.social_guard_dev.ui.tasks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

class TaskViewModel : ViewModel() {
    var itemBoxes = MutableLiveData<MutableList<ItemBox>?>()

    init {
        itemBoxes.value = mutableListOf()
    }

    fun addTaskItem(newTask: ItemBox)
    {
        val list = itemBoxes.value
        list!!.add(newTask)
        itemBoxes.postValue(list)
    }

    fun updateTaskItem(id: UUID, name: String, desc: String, dueTime: LocalTime?)
    {
        val list = itemBoxes.value
        val task = list!!.find { it.id == id }!!
        task.name = name
        task.desc = desc
        task.due = dueTime
        itemBoxes.postValue(list)
    }

    fun setCompleted(taskItem: ItemBox)
    {
        val list = itemBoxes.value
        val task = list!!.find { it.id == taskItem.id }!!
        if (task.dateOfComp == null)
            task.dateOfComp = LocalDate.now()
        itemBoxes.postValue(list)
    }
}