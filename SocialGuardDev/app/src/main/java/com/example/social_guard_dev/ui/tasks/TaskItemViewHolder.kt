package com.example.social_guard_dev.ui.tasks

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.social_guard_dev.databinding.TaskItemBoxBinding
import java.time.format.DateTimeFormatter

class TaskItemViewHolder(
    private val context: Context,
    private val binding: TaskItemBoxBinding,
    private val clickListener: TaskItemClickListener
): RecyclerView.ViewHolder(binding.root)
{
    private val timeFormat = DateTimeFormatter.ofPattern("HH:mm")

    fun bindTaskItem(itemBox: ItemBox)
    {
        binding.name.text = itemBox.name

        if (itemBox.completion()){
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.dueTime.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        binding.completedButton.setImageResource(itemBox.imageResource())
        binding.completedButton.setColorFilter(itemBox.imageColor(context))

        binding.completedButton.setOnClickListener{
            clickListener.completeTaskItem(itemBox)
        }
        binding.taskItemBox.setOnClickListener{
            clickListener.editTaskItem(itemBox)
        }

        if(itemBox.due != null)
            binding.dueTime.text = timeFormat.format(itemBox.due)
        else
            binding.dueTime.text = ""
    }
}