package com.example.timeandtune.DAL

import android.util.Log
import com.example.timeandtune.DAL.Models.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CloudDataBase: DataBaseHelper {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val uid = firebaseAuth.currentUser!!.uid
    private val tasksReference = firebaseDatabase.getReference("users").child(uid).child("tasks")
    private val userReference = firebaseDatabase.getReference("users").child(uid)

    fun getUserReference(): DatabaseReference {
        return userReference
    }

    fun getUniqueIdForTask(): String {
        return tasksReference.push().key!!
    }

    override fun addTask(task: Task) {
        tasksReference.child(task.id).setValue(task)
    }

    override fun getTasks(): List<Task> {
        val taskList = mutableListOf<Task>()
        tasksReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (taskSnapshot in dataSnapshot.children) {
                    val task = taskSnapshot.getValue(Task::class.java)
                    if (task != null) {
                        taskList.add(task)
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("TimeAndTune", "Error: ${databaseError.message}")
            }
        })

        return taskList.toList()
    }

    override fun updateTask(task: Task) {
        tasksReference.child(task.id).updateChildren(task.getHasMap())
    }
}