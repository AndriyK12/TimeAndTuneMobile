package com.example.timeandtune.Presentation

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.timeandtune.DAL.CloudDataBase
import com.example.timeandtune.DAL.LocalDataBase
import com.example.timeandtune.DAL.Models.Task
import com.example.timeandtune.R
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale




class AddTask : AppCompatActivity() {

    private lateinit var dbAdapter: LocalDataBase
    private lateinit var cloudDataBase: CloudDataBase
    private lateinit var nameEditText: TextInputEditText
    private lateinit var dateEditText: TextInputEditText
    private lateinit var priorityEditText: TextInputEditText
    private lateinit var descriptionEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_task)

        nameEditText = findViewById(R.id.nameText)
        dateEditText = findViewById(R.id.dateText)
        priorityEditText = findViewById(R.id.priorText)
        descriptionEditText = findViewById(R.id.descText)

        val createButton = findViewById<Button>(R.id.createButton)
        createButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val date = dateEditText.text.toString().trim()
            val priority = priorityEditText.text.toString().trim()
            val description = descriptionEditText.text.toString().trim()

            saveTask(name,date, priority, description)

            finish()
        }
    }
    private fun saveTask(name: String, date: String, priority: String, description: String) {
        val sdf = SimpleDateFormat("yyyy MM dd HH mm", Locale.getDefault())
        val currentDate = Date()
        val formattedDate: String = sdf.format(currentDate)
        dbAdapter = LocalDataBase(this)
        cloudDataBase = CloudDataBase()
        dbAdapter.addTask(
            Task(
            cloudDataBase.getUniqueIdForTask(),
            name,
            description,
            "2024 07 22 14 30",
            date,
                formattedDate,
            priority.toInt(),
            false,
            "0"
            )
        )

    }
}
