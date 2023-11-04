package com.example.puppyappparcial.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.puppyappparcial.R
import com.example.puppyappparcial.databinding.ActivityMain2Binding
import com.example.puppyappparcial.fragments.Adoption
import com.example.puppyappparcial.fragments.Config
import com.example.puppyappparcial.fragments.Favourites
import com.example.puppyappparcial.fragments.Home
import com.example.puppyappparcial.fragments.Profile
import com.example.puppyappparcial.fragments.Publication
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import com.google.android.material.navigation.NavigationView

@AndroidEntryPoint
class MainActivity2 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fragmentManager: FragmentManager
    private lateinit var toolbar: Toolbar
    private lateinit var transaction: FragmentTransaction
    private lateinit var navigationView: NavigationView
    private lateinit var bottomNavigationButton: BottomAppBar
    private lateinit var actionBackItem: MenuItem
    private lateinit var nombre: String
    private lateinit var imagenUrl: String
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var navHostFrag: NavHostFragment
    private lateinit var binding: ActivityMain2Binding

    private val dogViewModel: DogViewModel by viewModels()
    private var breed: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.inflateMenu(R.menu.side_menu)

        nombre = intent.getStringExtra("nombre").toString()
        imagenUrl = intent.getStringExtra("imagenUrl").toString()

        drawerLayout = findViewById(R.id.drawer_layout)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationButton = findViewById(R.id.bottomAppBar)

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView = findViewById(R.id.navigation_drawer)
        navigationView.setNavigationItemSelectedListener(this)
        navigationView.setCheckedItem(R.id.action_back)
        actionBackItem = toolbar.menu.findItem(R.id.action_back)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.background = null

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    openFragment(Home())
                    return@setOnItemSelectedListener true
                }
                R.id.adoption -> {
                    openFragment(Adoption())
                    return@setOnItemSelectedListener true
                }
                R.id.publi -> {
                    openFragment(Publication())
                    return@setOnItemSelectedListener true
                }
                R.id.fav -> {
                    openFragment(Favourites())
                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener false
                }
            }
        }

        fragmentManager = supportFragmentManager
        openFragment(Home())

        //setupBottomNav()

        //dogViewModel.onCreate()

//        dogViewModel.dogModel.observe(this, Observer {
//            binding.breedName.text = it.message.random()
//
//            breed = binding.breedName.text as String
//
//            dogViewModel.getSubBreed(breed)
//            dogViewModel.subBreedModel.observe(this, Observer {
//                if (!it.message.isNullOrEmpty()) {
//                    for (i in 0 .. it.message.size - 1){
//                        val subRazaActual = binding.subBreed.text
//                        val nuevoSubRaza = if (subRazaActual.isEmpty()) it.message[i]
//                        else "$subRazaActual\n${it.message[i]}"
//
//                        binding.subBreed.text = nuevoSubRaza
//                    }
//                }
//            })
//        })

    }


    override fun onSupportNavigateUp(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        return false
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == R.id.perfil) {
            toolbar.title = "Perfil"
            bottomNavigationButton.visibility = View.GONE
            openFragment(Profile())

            // Oculta el elemento de retroceso
        } else if (itemId == R.id.config) {
            toolbar.title = "Configuracion"
            bottomNavigationButton.visibility = View.GONE
            openFragment(Config())

            // Oculta el elemento de retroceso
        } else if (itemId == R.id.action_back) {
            toolbar.title = ""
            bottomNavigationButton.visibility = View.VISIBLE
            openFragment(Home())
            bottomNavigationView.selectedItemId = R.id.home
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun openFragment(fragment: Fragment) {
         transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    private fun openFragmentWithArguments(fragment: Fragment) {
        val bundle = Bundle()
        bundle.putString("nombre", nombre)
        bundle.putString("imagenUrl", imagenUrl)
        fragment.arguments = bundle

        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

}
