package com.example.timeandtune.DAL.Models

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timeandtune.Presentation.AddTask
import com.example.timeandtune.Presentation.AdditionalInfo
import com.example.timeandtune.Presentation.Auth.SignIn
import com.example.timeandtune.R

class TasksAdapter(private val tasksList: List<Task>, private val context: Context):
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
        val context = holder.taskTitle.context
        holder.taskDate.text = currentItem.expectedFinishTime.toDate()
        holder.taskTimer.text = currentItem.executionTime
        holder.taskTitle.text = currentItem.name

        holder.taskTitle.setOnClickListener {
            Log.d("myLog", "Works")
            val intent_ = Intent(context, AdditionalInfo::class.java)
            intent_.putExtra("task", currentItem)
            context.startActivity(intent_)
        }
    }

    class taskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val taskTitle: TextView = itemView.findViewById(R.id.listItemTitle)
        val taskTimer: TextView = itemView.findViewById(R.id.listItemTimer)
        val taskDate: TextView = itemView.findViewById(R.id.listItemDate)

    }

}

private fun String.toDate(): String {
    val parts = this.split(" ")
    return parts[1] +"." + parts[2]
}
