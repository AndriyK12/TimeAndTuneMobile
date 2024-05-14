package com.example.timeandtune.Presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.timeandtune.DAL.Models.Task
import com.example.timeandtune.databinding.ActivityAdditionalInfoBinding

class AdditionalInfo : AppCompatActivity() {
    private lateinit var binding: ActivityAdditionalInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdditionalInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val task = intent.getSerializableExtra("task") as Task

        binding.nameField.setText(task.name)
        binding.dateField.setText(task.expectedFinishTime.toString())
        binding.descriptionField.setText(task.description)
        binding.priorityField.setText(task.priority)

        binding.updateButton.setOnClickListener {
            val newName = binding.nameField.text.toString()
            val newDate = binding.nameField.text.toString()
            val newDescription = binding.nameField.text.toString()
            val newPriority = binding.nameField.text.toString()

            val newTask = Task(
                task.id,
                newName,
                newDescription,
                task.dateOfCreation,
                newDate,
                "",
                newPriority.toInt(),
                task.completed,
                "0"
            )

        }

    }
}