package com.example.timeandtune.DAL.Models

data class Task(
    val id: String,
    val name: String,
    val description: String,
    val dateOfCreation: DateTime,
    val expectedFinishTime: DateTime,
    val finishTime: DateTime,
    val priority: Int,
    val completed: Boolean,
    val executionTime: Time
): java.io.Serializable {
    fun getHasMap(): Map<String, Any> {
        return mapOf(
            "id" to this.id,
            "name" to this.name,
            "description" to this.description,
            "dateOfCreation" to this.dateOfCreation,
            "expectedFinishTime" to this.expectedFinishTime,
            "finishTime" to this.finishTime,
            "priority" to this.priority,
            "completed" to this.completed,
            "executionTime" to this.executionTime
        )
    }
}
