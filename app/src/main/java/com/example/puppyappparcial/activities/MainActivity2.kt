package com.example.puppyappparcial.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.puppyappparcial.R
import com.example.puppyappparcial.data.GetDogsUseCase
import com.example.puppyappparcial.databinding.ActivityMainBinding
import com.example.puppyappparcial.domain.Dog
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity2 : AppCompatActivity() {
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var navHostFrag: NavHostFragment

    private val dogViewModel: DogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        bottomNavView = findViewById(R.id.bottomNav)
        navHostFrag = supportFragmentManager.findFragmentById(R.id.navigationHost) as NavHostFragment

        setupBottomNav()

        dogViewModel.onCreate()
    }

    private fun setupBottomNav () {
        NavigationUI.setupWithNavController(bottomNavView,navHostFrag.navController)
    }

}