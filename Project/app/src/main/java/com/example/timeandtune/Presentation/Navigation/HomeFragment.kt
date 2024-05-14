package com.example.timeandtune.Presentation.Navigation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timeandtune.DAL.LocalDataBase
import com.example.timeandtune.DAL.Models.Task
import com.example.timeandtune.DAL.Models.TasksAdapter
import com.example.timeandtune.Presentation.AddTask
import com.example.timeandtune.R
import com.example.timeandtune.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {
    private lateinit var dbAdapter: LocalDataBase

    private lateinit var tasksRecyclerView: RecyclerView
    private lateinit var tasksList: List<Task>

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        dbAdapter = LocalDataBase(requireContext())

        tasksList = dbAdapter.getTasks()

        tasksRecyclerView = binding.recyclerView
        tasksRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        tasksRecyclerView.setHasFixedSize(true)

        tasksRecyclerView.adapter = TasksAdapter(tasksList)

        binding.newTaskButton.setOnClickListener {
            val intent = Intent(requireContext(), AddTask::class.java)
            startActivity(intent)
        }

        return view
    }

}