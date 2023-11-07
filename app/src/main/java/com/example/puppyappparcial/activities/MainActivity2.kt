package com.example.puppyappparcial.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.puppyappparcial.R
import com.example.puppyappparcial.data.DogRepository
import com.example.puppyappparcial.data.database.entities.PublicationEntity
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity2: AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
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
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var navHostFrag: NavHostFragment
    private lateinit var binding: ActivityMain2Binding
    @Inject
    lateinit var repository: DogRepository
    private lateinit var p1 : PublicationEntity
    private lateinit var p2 : PublicationEntity
    private lateinit var p3 : PublicationEntity
    private lateinit var p4 : PublicationEntity
    private lateinit var p5 : PublicationEntity
    private lateinit var p6 : PublicationEntity
    private lateinit var p7 : PublicationEntity
    private lateinit var p8 : PublicationEntity

    private val dogViewModel: DogViewModel by viewModels()
    private var breed: String = ""
    private lateinit var imageUrl: String
    private lateinit var telefono: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        nombre = intent.getStringExtra("nombre").toString()
        imageUrl = intent.getStringExtra("imagenUrl").toString()
        telefono = intent.getStringExtra("telefono").toString()
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = findViewById(R.id.toolbar)

        //toolbar.setTitleTextAppearance(this, poppins_bold)
        toolbar.title = ""
        toolbar.setTitleTextAppearance(this, R.style.ToolbarTheme)
        setSupportActionBar(toolbar)
        toolbar.inflateMenu(R.menu.side_menu)


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
                    toolbar.title = ""
                    openFragmentWithArguments(Home())
                    return@setOnItemSelectedListener true
                }
                R.id.adoption -> {
                    toolbar.title = "Adopciones"
                    openFragmentWithArguments(Adoption())
                    return@setOnItemSelectedListener true
                }
                R.id.publi -> {
                    toolbar.title = "Publicación"
                    openFragmentWithArguments(Publication())
                    return@setOnItemSelectedListener true
                }
                R.id.fav -> {
                    toolbar.title = "Mis Favoritos"
                    openFragmentWithArguments(Favourites())
                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener false
                }
            }
        }

        fragmentManager = supportFragmentManager
        openFragmentWithArguments(Home())

        p1 = PublicationEntity(
            1,
            "Bouvier",
            "",
            "Rocky",
            5,
            "Macho",
            "Rocky es un bouvier juguetón y enérgico. Le encanta jugar a la pelota.",
            20F,
            "Bs As",
            "https://images.dog.ceo/breeds/bouvier/n02106382_1365.jpg",
            "Juan",
            "https://images.dog.ceo/breeds/retriever-curly/n02099429_121.jpg",
            "1187996047",
            false,
            false,
        )

        p2 = PublicationEntity(
            2,
            "Papillon",
            "",
            "Luna",
            3,
            "Hembra",
            " Luna es una papillon con un pelaje blanco, negro y marron. Disfrutan de jugar afuera.",
            6F,
            "Cordoba",
            "https://images.dog.ceo/breeds/papillon/n02086910_4609.jpg",
            "Maria",
            "https://images.dog.ceo/breeds/retriever-curly/n02099429_121.jpg",
            "1547789470",
            false,
            false
        )

        p3 = PublicationEntity(
            3,
            "Chihuahua",
            "",
            "Max",
            8,
            "Macho",
            "Max es un perro leal y obediente, aunque, a veces, tiene un temperamento fuerte",
            3F,
            "San Luis",
            "https://images.dog.ceo/breeds/chihuahua/n02085620_13151.jpg",
            "Carlos",
            "https://images.dog.ceo/breeds/retriever-curly/n02099429_121.jpg",
            "345678909",
            false,
            false
        )

        p4 = PublicationEntity(
            4,
            "Komondor",
            "",
            "Coco",
            6,
            "Macho",
            "Coco es un Komondor con un pelaje suave pero dificil de cuidar. Adora jugar en el parque con otros perros",
            33F,
            "Bs As",
            "https://images.dog.ceo/breeds/komondor/n02105505_1657.jpg",
            "Ana",
            "https://images.dog.ceo/breeds/retriever-curly/n02099429_121.jpg",
            "12345678",
            false,
            false
        )
        p5 = PublicationEntity(
            5,
            "Segugio",
            "Italian",
            "Bella",
            8,
            "Hembra",
            "Le encanta pasear y jugar en el parque",
            12F,
            "San Juan",
            "https://images.dog.ceo/breeds/segugio-italian/n02090722_002.jpg",
            "Sofia",
            "",
            "123123",
            false,
            true
        )

        p6 = PublicationEntity(
            6,
            "Sharpei",
            "",
            "Buddy",
            10,
            "Macho",
            "Buddy es un sharpei amigable y juguetón que siempre tiene la cola en movimiento.",
            33F,
            "Bs As",
            "https://images.dog.ceo/breeds/sharpei/noel.jpg",
            "Ana",
            "",
            "123123",
            false,
            true
        )
        p7 = PublicationEntity(
            7,
            "Papillon",
            "",
            "Luna",
            3,
            "Hembra",
            " Luna es una papillon con un pelaje blanco, negro y marron. Disfrutan de jugar afuera.",
            6F,
            "Cordoba",
            "https://images.dog.ceo/breeds/australian-shepherd/pepper.jpg",
            "Maria",
            "",
            "123123",
            true,
            false,

        )

        p8 = PublicationEntity(
            8,
            "Komondor",
            "",
            "Coco",
            6,
            "Macho",
            "Coco es un Komondor con un pelaje suave pero dificil de cuidar. Adora jugar en el parque con otros perros",
            33F,
            "Bs As",
            "https://images.dog.ceo/breeds/keeshond/n02112350_7141.jpg",
            "Ana",
            "",
            "123123",
            true,
            false
        )

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            repository.insertPublication(p1)
            repository.insertPublication(p2)
            repository.insertPublication(p3)
            repository.insertPublication(p4)
            repository.insertPublication(p5)
            repository.insertPublication(p6)
            repository.insertPublication(p7)
            repository.insertPublication(p8)
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == R.id.perfil) {
            toolbar.title = "Perfil"
            bottomNavigationButton.visibility = View.GONE
            openFragmentWithArguments(Profile())

            // Oculta el elemento de retroceso
        } else if (itemId == R.id.config) {
            toolbar.title = "Configuracion"
            bottomNavigationButton.visibility = View.GONE
            openFragmentWithArguments(Config())

            // Oculta el elemento de retroceso
        } else if (itemId == R.id.action_back) {
            toolbar.title = ""
            bottomNavigationButton.visibility = View.VISIBLE
            openFragmentWithArguments(Home())
            bottomNavigationView.selectedItemId = R.id.home
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun openFragmentWithArguments(fragment: Fragment) {
        val bundle = Bundle()
        bundle.putString("nombre", nombre)
        bundle.putString("imagenUrl", imageUrl)
        bundle.putString("telefono", telefono)
        fragment.arguments = bundle

        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

}
