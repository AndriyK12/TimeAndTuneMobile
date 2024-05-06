package com.example.timeandtune.DAL.Models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timeandtune.R

class TasksAdapter(private val tasksList: List<Task>):
    RecyclerView.Adapter<TasksAdapter.taskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): taskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,
            parent,false)
        return taskViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return  tasksList.size
    }

    override fun onBindViewHolder(holder: taskViewHolder, position: Int) {
        val currentItem = tasksList[position]
        holder.taskDate.text = currentItem.expectedFinishTime.toString()
        holder.taskTimer.text = currentItem.executionTime.toString()
        holder.taskTitle.text = currentItem.name
    }

    class taskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val taskTitle: TextView = itemView.findViewById(R.id.listItemTitle)
        val taskTimer: TextView = itemView.findViewById(R.id.listItemTimer)
        val taskDate: TextView = itemView.findViewById(R.id.listItemDate)

    }

}