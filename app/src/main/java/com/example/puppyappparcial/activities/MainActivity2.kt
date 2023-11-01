package com.example.puppyappparcial.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.puppyappparcial.R
import com.example.puppyappparcial.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity() {
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var navHostFrag: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        bottomNavView = findViewById(R.id.bottomNav)
        navHostFrag = supportFragmentManager.findFragmentById(R.id.navigationHost) as NavHostFragment

        setupBottomNav()
    }

    private fun setupBottomNav () {
        NavigationUI.setupWithNavController(bottomNavView,navHostFrag.navController)
    }

}