package com.example.timeandtune.DAL.Models

data class Task(
    val id: Int,
    val name: String,
    val description: String,
    val dateOfCreation: DateTime,
    val expectedFinishTime: DateTime,
    val finishTime: DateTime,
    val priority: Short,
    val completed: Boolean,
    val executionTime: Time
)
