package com.example.timeandtune.Presentation.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.timeandtune.DAL.CloudDataBase
import com.example.timeandtune.Presentation.Navigation.MainPage
import com.example.timeandtune.R
import com.example.timeandtune.databinding.SignInLayoutBinding
import com.google.firebase.auth.FirebaseAuth

class SignIn : AppCompatActivity() {
    private lateinit var binding: SignInLayoutBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signInButton.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, MainPage::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Invalid login or password", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                Toast.makeText(this, "All fields must be filled in", Toast.LENGTH_SHORT).show()
            }
        }

        binding.createAccountButton.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }
}