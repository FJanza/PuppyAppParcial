package com.example.puppyappparcial.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.puppyappparcial.R
import com.example.puppyappparcial.databinding.ActivityMain2Binding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity2 : AppCompatActivity() {
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var navHostFrag: NavHostFragment
    private lateinit var binding: ActivityMain2Binding

    private val dogViewModel: DogViewModel by viewModels()
    private var breed: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavView = binding.bottomNav
        navHostFrag = supportFragmentManager.findFragmentById(R.id.navigationHost) as NavHostFragment

        setupBottomNav()

        dogViewModel.onCreate()

        dogViewModel.dogModel.observe(this, Observer {
            binding.breedName.text = it.message.random()

            breed = binding.breedName.text as String

            dogViewModel.getSubBreed(breed)
                dogViewModel.subBreedModel.observe(this, Observer {
                    if (!it.message.isNullOrEmpty()) {
                        for (i in 0 .. it.message.size - 1){
                            val subRazaActual = binding.subBreed.text
                            val nuevoSubRaza = if (subRazaActual.isEmpty()) it.message[i]
                            else "$subRazaActual\n${it.message[i]}"

                            binding.subBreed.text = nuevoSubRaza
                        }
                    }
                })
        })
    }

    private fun setupBottomNav () {
        NavigationUI.setupWithNavController(bottomNavView,navHostFrag.navController)
    }

}