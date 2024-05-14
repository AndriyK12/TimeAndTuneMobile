package com.example.timeandtune.Presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.timeandtune.DAL.CloudDataBase
import com.example.timeandtune.DAL.LocalDataBase
import com.example.timeandtune.DAL.Models.Task
import com.example.timeandtune.Presentation.Navigation.MainPage
import com.example.timeandtune.databinding.ActivityAdditionalInfoBinding

class AdditionalInfo : AppCompatActivity() {
    private lateinit var localDataBase: LocalDataBase
    private lateinit var cloudDataBase: CloudDataBase

    private lateinit var binding: ActivityAdditionalInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdditionalInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val task = intent.getSerializableExtra("task") as Task

        localDataBase = LocalDataBase(this)
        cloudDataBase = CloudDataBase()

        binding.nameField.setText(task.name.toString())
        binding.dateField.setText(task.expectedFinishTime.toString())
        binding.descriptionField.setText(task.description.toString())
        binding.priorityField.setText(task.priority.toString())

        binding.updateButton.setOnClickListener {
            val newName = binding.nameField.text.toString()
            val newDate = binding.dateField.text.toString()
            val newDescription = binding.descriptionField.text.toString()
            val newPriority = binding.priorityField.text.toString()

            val newTask = Task(
                task.id,
                newName,
                newDescription,
                task.dateOfCreation,
                newDate,
                task.finishTime,
                newPriority.toInt(),
                task.completed,
                task.executionTime
            )
            localDataBase.updateTask(newTask)
            cloudDataBase.updateTask(newTask)

            val intent = Intent(this, MainPage::class.java)
            startActivity(intent)
        }

        binding.finishButton.setOnClickListener {
            val newName = binding.nameField.text.toString()
            val newDate = binding.dateField.text.toString()
            val newDescription = binding.descriptionField.text.toString()
            val newPriority = binding.priorityField.text.toString()

            val newTask = Task(
                task.id,
                newName,
                newDescription,
                task.dateOfCreation,
                newDate,
                task.finishTime,
                newPriority.toInt(),
                true,
                task.executionTime
            )
            localDataBase.updateTask(newTask)
            cloudDataBase.updateTask(newTask)

            val intent = Intent(this, MainPage::class.java)
            startActivity(intent)
        }
    }
}