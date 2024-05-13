package com.example.timeandtune.Presentation.Navigation

import android.content.Context
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
import com.example.timeandtune.R

class HomeFragment : Fragment() {
    private lateinit var dbAdapter: LocalDataBase

    private lateinit var tasksRecyclerView: RecyclerView
    private lateinit var tasksList: List<Task>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.home_fragment, container, false)

        dbAdapter = LocalDataBase(requireContext())

        tasksList = dbAdapter.getTasks()

        tasksRecyclerView = view.findViewById(R.id.recyclerView)
        tasksRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        tasksRecyclerView.setHasFixedSize(true)

        tasksRecyclerView.adapter = TasksAdapter(tasksList)

        return view
    }

}