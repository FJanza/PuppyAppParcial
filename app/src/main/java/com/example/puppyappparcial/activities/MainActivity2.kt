package com.example.puppyappparcial.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.puppyappparcial.R
import com.example.puppyappparcial.data.GetDogsUseCase
import com.example.puppyappparcial.databinding.ActivityMain2Binding
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