package com.example.timeandtune.DAL

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.timeandtune.DAL.Models.DateTime
import com.example.timeandtune.DAL.Models.Task
import com.example.timeandtune.DAL.Models.Time

class LocalDataBase(private val context: Context): DataBaseHelper{
    private val database: SQLiteDatabase by lazy {
        SQLiteHelper(context).writableDatabase
    }

    override fun addTask(task: Task) {
        val values = ContentValues().apply {
            put(SQLiteHelper.NAME, task.name)
            put(SQLiteHelper.DESCRIPTION, task.description)
            put(SQLiteHelper.DATE_OF_CREATION, task.dateOfCreation.toString())
            put(SQLiteHelper.EXECUTION_TIME, task.executionTime.toString())
            put(SQLiteHelper.EXPECTED_FINISH_TIME, task.expectedFinishTime.toString())
            put(SQLiteHelper.FINISH_TIME, task.finishTime.toString())
            put(SQLiteHelper.PRIORITY, task.priority)
            put(SQLiteHelper.COMPLETED, task.completed)
            put(SQLiteHelper.EXECUTION_TIME, task.executionTime.toString())
        }
        database.insert(SQLiteHelper.TASK_TABLE, null, values)
    }

    @SuppressLint("Range")
    override fun getTasks(): List<Task> {
        val tasks = mutableListOf<Task>()
        val query = "SELECT * FROM ${SQLiteHelper.TASK_TABLE}"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do{
                val id = cursor.getString(cursor.getColumnIndex(SQLiteHelper.ID))
                val name = cursor.getString(cursor.getColumnIndex(SQLiteHelper.NAME))
                val description = cursor.getString(cursor.getColumnIndex(SQLiteHelper.DESCRIPTION))
                val dateOfCreation = cursor.getString(cursor.getColumnIndex(SQLiteHelper.DATE_OF_CREATION))
                val expectedFinishTime = cursor.getString(cursor.getColumnIndex(SQLiteHelper.EXPECTED_FINISH_TIME))
                val finishTime = cursor.getString(cursor.getColumnIndex(SQLiteHelper.FINISH_TIME))
                val priority = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.PRIORITY))
                val completed = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COMPLETED))
                val executionTime = cursor.getString(cursor.getColumnIndex(SQLiteHelper.EXECUTION_TIME))
                tasks.add(
                    Task(
                        id,
                        name,
                        description,
                        DateTime.parseDateTime(dateOfCreation),
                        DateTime.parseDateTime(expectedFinishTime),
                        DateTime.parseDateTime(finishTime),
                        priority,
                        completed==1,
                        Time.parseTime(executionTime)
                    )
                )
            }while(cursor.moveToNext())
        }
        return tasks
    }

    override fun updateTask(task: Task) {
        val values = ContentValues().apply {
            put(SQLiteHelper.NAME, task.name)
            put(SQLiteHelper.DESCRIPTION, task.description)
            put(SQLiteHelper.DATE_OF_CREATION, task.dateOfCreation.toString())
            put(SQLiteHelper.EXECUTION_TIME, task.executionTime.toString())
            put(SQLiteHelper.EXPECTED_FINISH_TIME, task.expectedFinishTime.toString())
            put(SQLiteHelper.FINISH_TIME, task.finishTime.toString())
            put(SQLiteHelper.PRIORITY, task.priority)
            put(SQLiteHelper.COMPLETED, task.completed)
            put(SQLiteHelper.EXECUTION_TIME, task.executionTime.toString())
        }

        val whereClause = "${SQLiteHelper.ID} = ?"
        val whereArgs = arrayOf(task.id)

        database.update(SQLiteHelper.TASK_TABLE, values, whereClause, whereArgs)
    }
}