package com.example.social_guard_dev.ui.tasks

interface TaskItemClickListener
{
    fun editTaskItem(itemBox: ItemBox)
    fun completeTaskItem(itemBox: ItemBox)
}