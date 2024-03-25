package com.example.timeandtune

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.timeandtune.DAL.DataBaseAdapter
import com.example.timeandtune.DAL.Models.DateTime
import com.example.timeandtune.DAL.Models.Task
import com.example.timeandtune.DAL.Models.Date
import com.example.timeandtune.DAL.Models.Time

class MainActivity : AppCompatActivity() {
    private lateinit var dataBaseAdapter: DataBaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataBaseAdapter = DataBaseAdapter(this)

        val bunchOfTasks = dataBaseAdapter.getTasks()
        val numberOfTasks = 20
        if(bunchOfTasks.size<numberOfTasks){
            for (i in 0..numberOfTasks){
                dataBaseAdapter.addTask(
                    Task(
                        0,
                        "Зробити завдання $i",
                        "Бажано сьогодні",
                        DateTime(Date(2024, "January", i.toShort()), Time(i.toShort(), 30, 0)),
                        DateTime(Date(2024, "January", i.toShort()), Time(i.toShort(), 30, 0)),
                        DateTime(Date(2024, "January", i.toShort()), Time(i.toShort(), 30, 0)),
                        (i%3).toShort(),
                        i%2==0,
                        Time(i.toShort(), 30, 0)
                    )
                )
            }
        }

        for (i in bunchOfTasks.indices){
            Log.d("myLog", "$i -> ${bunchOfTasks[i]}")
        }
    }
}