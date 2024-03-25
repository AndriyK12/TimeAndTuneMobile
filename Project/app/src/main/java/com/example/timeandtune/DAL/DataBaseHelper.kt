package com.example.timeandtune.DAL

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private const val DB_NAME = "TaskDataBase.db"
        private const val DB_VERSION = 1

        const val TASK_TABLE = "taskTable"
        const val ID = "ID"
        const val NAME = "name"
        const val DESCRIPTION = "description"
        const val DATE_OF_CREATION = "dateOfCreation"
        const val EXPECTED_FINISH_TIME = "expectedFinishTime"
        const val FINISH_TIME = "finishTime"
        const val PRIORITY = "priority"
        const val COMPLETED = "completed"
        const val EXECUTION_TIME = "executionTime"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTaskTable = "CREATE TABLE $TASK_TABLE " +
                "($ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$NAME TEXT NOT NULL, " +
                "$DESCRIPTION TEXT DEFAULT '', " +
                "$DATE_OF_CREATION DATE NOT NULL, " +
                "$EXPECTED_FINISH_TIME DATE NOT NULL, " +
                "$FINISH_TIME DATE DEFAULT '0001-01-01', " +
                "$PRIORITY INTEGER NOT NULL, " +
                "$COMPLETED BOOLEAN NOT NULL, " +
                "$EXECUTION_TIME TEXT)"
        db?.execSQL(createTaskTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TASK_TABLE")
    }
}