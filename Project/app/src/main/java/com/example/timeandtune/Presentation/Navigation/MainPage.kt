package com.example.timeandtune.Presentation.Navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.timeandtune.DAL.DataBaseAdapter
import com.example.timeandtune.DAL.Models.Date
import com.example.timeandtune.DAL.Models.DateTime
import com.example.timeandtune.DAL.Models.Task
import com.example.timeandtune.DAL.Models.Time
import com.example.timeandtune.R
import com.example.timeandtune.databinding.MainPageLayoutBinding

class MainPage : AppCompatActivity() {
    private lateinit var dataBaseAdapter: DataBaseAdapter
    private lateinit var binding: MainPageLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainPageLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        replaceFragment(HomeFragment())

        binding.navigationMenu.setOnItemSelectedListener{menuItem ->
            when (menuItem.itemId) {
                R.id.homePage -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.stasticPage -> {
                    replaceFragment(StatisticFragment())
                    true
                }
                R.id.focusPage -> {
                    replaceFragment(FocusFragment())
                    true
                }
                R.id.profilePage -> {
                    replaceFragment(ProfileFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.pageFragment, fragment)
            .commit()
    }
}