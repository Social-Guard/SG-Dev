package com.example.social_guard_dev.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.social_guard_dev.databinding.FragmentTasksBinding

class TasksFragment : Fragment(), TaskItemClickListener {
    private lateinit var binding: FragmentTasksBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskViewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)

        binding.newTaskButton.setOnClickListener {
            ToDoBox(null).show(parentFragmentManager, "newTaskTag")
        }

        setRecyclerView()
    }

    private fun setRecyclerView() {
        taskViewModel.itemBoxes.observe(viewLifecycleOwner) { tasks ->
            binding.todoListRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = TaskItemAdapter(tasks!!, this@TasksFragment)
            }
        }
    }

    override fun editTaskItem(itemBox: ItemBox) {
        ToDoBox(itemBox).show(parentFragmentManager, "newTaskTag")
    }

    override fun completeTaskItem(itemBox: ItemBox) {
        taskViewModel.setCompleted(itemBox)
    }
}