package com.example.timeandtune.Presentation.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.timeandtune.R
import com.example.timeandtune.databinding.SignUpLayoutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    private lateinit var binding: SignUpLayoutBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        val usersRef = firebaseDatabase.getReference("users")

        binding.signUpButton.setOnClickListener {
            val name = binding.username.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val passwordConfirm = binding.passwordConfirmation.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()) {
                if (password == passwordConfirm) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val uid = firebaseAuth.currentUser!!.uid
                                usersRef.child(uid).child("name").setValue(name)
                                val intent = Intent(this, SignIn::class.java)
                                startActivity(intent)
                            }
                        }
                        .addOnFailureListener { exception ->
                            if (exception is FirebaseAuthUserCollisionException) {
                                Toast.makeText(
                                    this,
                                    "An account with such mail already exists",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    this,
                                    "Error during registration: ${exception.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "All fields must be filled in", Toast.LENGTH_SHORT).show()
            }
        }

        binding.signInButton.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
    }
}