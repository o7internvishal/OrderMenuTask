package com.example.O7Solution


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.navigation.NavController

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.O7Solution.model.UserData

import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    //    val usersList: Any
    lateinit var bottomNavigationView: BottomNavigationView
    var userList = ArrayList<UserData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        var navController: NavController = navHostFragment.navController

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        setupWithNavController(bottomNavigationView, navController)
    }


}


