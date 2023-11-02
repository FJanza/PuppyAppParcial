package com.example.puppyappparcial.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
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
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.puppyappparcial.R
import com.example.puppyappparcial.fragments.Adoption
import com.example.puppyappparcial.fragments.Config
import com.example.puppyappparcial.fragments.Favourites
import com.example.puppyappparcial.fragments.Home
import com.example.puppyappparcial.fragments.Profile
import com.example.puppyappparcial.fragments.Publication
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity2 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fragmentManager: FragmentManager
    private lateinit var toolbar: Toolbar
    private lateinit var transaction: FragmentTransaction
    private lateinit var navigationView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState()

        navigationView = findViewById(R.id.navigation_drawer)
        navigationView.setNavigationItemSelectedListener(this)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setBackground(null)

        bottomNavigationView.setOnItemSelectedListener{ item ->
            val itemId = item.itemId
            when (itemId) {
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
                else -> false
            }
        }

        fragmentManager = supportFragmentManager;
        openFragment(Home());

    }

    private fun setupUpSideNav(navController: NavController) {
        navigationView.setupWithNavController(navController)

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        navController.addOnDestinationChangedListener { _, _, _ ->
            supportActionBar?.setHomeAsUpIndicator(R.drawable.hamburger)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        return false
    }

    override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
        var itemId = item.getItemId();
        if(itemId == R.id.perfil){
            openFragment(Profile())
        } else if(itemId == R.id.config) {
            openFragment(Config())
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true;
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed();
        }
    }
    private fun openFragment(fragment: Fragment) {
         transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

}
