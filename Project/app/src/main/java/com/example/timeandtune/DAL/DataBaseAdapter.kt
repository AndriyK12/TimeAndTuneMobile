package com.example.timeandtune.DAL

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.timeandtune.DAL.Models.DateTime
import com.example.timeandtune.DAL.Models.Task
import com.example.timeandtune.DAL.Models.Time

class DataBaseAdapter(private val context: Context) {
    private val database: SQLiteDatabase by lazy {
        DataBaseHelper(context).writableDatabase
    }

    fun addTask(task: Task): Long {
        val values = ContentValues().apply {
            put(DataBaseHelper.NAME, task.name)
            put(DataBaseHelper.DESCRIPTION, task.description)
            put(DataBaseHelper.DATE_OF_CREATION, task.dateOfCreation.toString())
            put(DataBaseHelper.EXECUTION_TIME, task.executionTime.toString())
            put(DataBaseHelper.EXPECTED_FINISH_TIME, task.expectedFinishTime.toString())
            put(DataBaseHelper.FINISH_TIME, task.finishTime.toString())
            put(DataBaseHelper.PRIORITY, task.priority)
            put(DataBaseHelper.COMPLETED, task.completed)
            put(DataBaseHelper.EXECUTION_TIME, task.executionTime.toString())
        }
        return database.insert(DataBaseHelper.TASK_TABLE, null, values)
    }

    @SuppressLint("Range")
    fun getTasks(): List<Task> {
        val tasks = mutableListOf<Task>()
        val query = "SELECT * FROM ${DataBaseHelper.TASK_TABLE}"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do{
                val id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.ID))
                val name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.NAME))
                val description = cursor.getString(cursor.getColumnIndex(DataBaseHelper.DESCRIPTION))
                val dateOfCreation = cursor.getString(cursor.getColumnIndex(DataBaseHelper.DATE_OF_CREATION))
                val expectedFinishTime = cursor.getString(cursor.getColumnIndex(DataBaseHelper.EXPECTED_FINISH_TIME))
                val finishTime = cursor.getString(cursor.getColumnIndex(DataBaseHelper.FINISH_TIME))
                val priority = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.PRIORITY))
                val completed = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COMPLETED))
                val executionTime = cursor.getString(cursor.getColumnIndex(DataBaseHelper.EXECUTION_TIME))
                //Log.d("Task", "$id, $name, $description, $dateOfCreation, $expectedFinishTime, $finishTime, $priority, $completed, $executionTime")
                tasks.add(
                    Task(
                        id,
                        name,
                        description,
                        DateTime.parseDateTime(dateOfCreation),
                        DateTime.parseDateTime(expectedFinishTime),
                        DateTime.parseDateTime(finishTime),
                        priority.toShort(),
                        completed==1,
                        Time.parseTime(executionTime)
                    )
                )
            }while(cursor.moveToNext())
        }
        return tasks
    }
}