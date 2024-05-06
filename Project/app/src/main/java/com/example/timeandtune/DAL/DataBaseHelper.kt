package com.example.timeandtune.DAL

import com.example.timeandtune.DAL.Models.Task

interface DataBaseHelper {
    fun addTask(task: Task)

    fun getTasks(): List<Task>

    fun updateTask(task: Task)
}