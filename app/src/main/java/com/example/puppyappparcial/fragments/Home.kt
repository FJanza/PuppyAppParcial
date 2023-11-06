package com.example.puppyappparcial.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyappparcial.R
import com.example.puppyappparcial.activities.MainActivity2
import com.example.puppyappparcial.domain.GetBreedsUseCase
import com.example.puppyappparcial.domain.GetSubBreedUseCase
import com.example.puppyappparcial.domain.models.Publication
import com.example.puppyappparcial.recyclerViewPublications.listener.OnViewItemClickedListener
import com.example.puppyappparcial.recyclerViewPublications.adapter.PublicationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class Home @Inject constructor(
    //private val getBreedsUseCase: GetBreedsUseCase,
    //private val  getSubBreedUseCase: GetSubBreedUseCase
) : Fragment(), OnViewItemClickedListener {

    private lateinit var view: View
    private lateinit var recPerros : RecyclerView

    var publications : MutableList<Publication> = ArrayList()

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var publicationAdapter: PublicationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_home, container, false)

        recPerros = view.findViewById(R.id.rec_favorites)

        return view
    }

    override fun onStart() {
        super.onStart()
        val filterNav = view.findViewById<TextView>(R.id.filter)

        addDefaultPublications()

        //Configuración Obligatoria
        requireActivity()

        recPerros.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        publicationAdapter = PublicationAdapter(publications, this)

        recPerros.layoutManager = linearLayoutManager
        recPerros.adapter = publicationAdapter

        //loadNavGraph()

//        filterNav.setOnClickListener{
//            val intent = Intent(requireContext(), MainActivity2::class.java)
//            intent.putExtra("FilterFragment", Filters::class.java)
//            startActivity(intent)
////            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
////            val filterFragment = Filters(getBreedsUseCase, getSubBreedUseCase)
////            fragmentTransaction.replace(R.id.fragment_container, filterFragment)
////            fragmentTransaction.addToBackStack(null)
////            fragmentTransaction.commit()
//            //navigateToMoreFilters()
//        }

    }

//    private fun navigateToMoreFilters() {
//
//        view.findNavController().navigate(R.id.action_home_to_filters)
//
//
//    }

    override fun onViewItemDetail(publication: com.example.puppyappparcial.domain.models.Publication) {
        //val action = listFragmentDirections.actionListFragmentToViewItem(perro)
        //this.findNavController().navigate(action)
        //Snackbar.make(view,perro.nombre, Snackbar.LENGTH_SHORT).show()
        Toast.makeText(context, "Detalle", Toast.LENGTH_SHORT).show()
    }

    private fun addDefaultPublications(){
        publications.add(com.example.puppyappparcial.domain.models.Publication(
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
            false,
            false
        ))

        publications.add(com.example.puppyappparcial.domain.models.Publication(
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
            false,
            false
        ))

        publications.add(com.example.puppyappparcial.domain.models.Publication(
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
            false,
            false
        ))

        publications.add(com.example.puppyappparcial.domain.models.Publication(
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
            false,
            false
        ))
    }


}