package com.example.puppyappparcial.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.puppyappparcial.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity() {
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var navHostFrag: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        navHostFrag = supportFragmentManager.findFragmentById(R.id.navigationHost) as NavHostFragment
        bottomNavView = findViewById(R.id.bottomNav)
        NavigationUI.setupWithNavController(bottomNavView,navHostFrag.navController)


    }



}