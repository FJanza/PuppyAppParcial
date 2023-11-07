package com.example.puppyappparcial.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyappparcial.R
import com.example.puppyappparcial.data.DogRepository
import com.example.puppyappparcial.data.database.entities.PublicationEntity
import com.example.puppyappparcial.domain.GetBreedsUseCase
import com.example.puppyappparcial.domain.GetPublicationUseCase
import com.example.puppyappparcial.domain.GetPublicationsUseCase
import com.example.puppyappparcial.domain.models.Breed
import com.example.puppyappparcial.domain.models.Publication
import com.example.puppyappparcial.domain.models.toDomain
import com.example.puppyappparcial.recyclerViewPublications.listener.OnViewItemClickedListener
import com.example.puppyappparcial.recyclerViewPublications.adapter.PublicationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class Home : Fragment(), OnViewItemClickedListener, OnQueryTextListener {

    private lateinit var view: View
    private lateinit var recPerros : RecyclerView
    private lateinit var search_bar: SearchView
    @Inject
    lateinit var getBreedsUseCase: GetBreedsUseCase
    private lateinit var getPublicationUseCase: GetPublicationUseCase
    @Inject
    lateinit var repository: DogRepository
    private lateinit var p1 : PublicationEntity
    private lateinit var p2 : PublicationEntity
    private lateinit var p3 : PublicationEntity
    private lateinit var p4 : PublicationEntity


    var publications : MutableList<Publication> = ArrayList()
    var auxPublications: MutableList<Publication> = ArrayList()
    val dogBreeds = mutableListOf<Publication>()

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var publicationAdapter: PublicationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_home, container, false)

        recPerros = view.findViewById(R.id.rec_home)

        search_bar = view.findViewById(R.id.search_bar) as SearchView
        search_bar.setOnQueryTextListener(this)

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
           1187996047,
           false,
           true,
           false
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
           1547789470,
           false,
           true,
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
            345678909,
            false,
            true,
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
            12345678,
            false,
            true,
            false
        )

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            repository.insertPublication(p1)
            repository.insertPublication(p2)
            repository.insertPublication(p3)
            repository.insertPublication(p4)
            var dataFromDB = repository.getAllPublicationsFromDataBase()
            publications.addAll(dataFromDB)
            auxPublications.addAll(dataFromDB)

            requireActivity().runOnUiThread {
                publicationAdapter.notifyDataSetChanged()
            }
        }
    }
    private fun searchByBreed(breed: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getBreedsUseCase()
            val breeds = call.message
            if (breeds.contains(breed)) {
//                val databaseBreed = getPublicationUseCase(breed)
//                requireActivity().runOnUiThread {
//                    dogBreeds.clear()
//                    dogBreeds.add(databaseBreed)
//                    publicationAdapter.notifyDataSetChanged()

                val filteredData = auxPublications.filter { it.breed.lowercase() == breed }
                if (filteredData.isNotEmpty()) {
                    publications.clear()
                    publications.addAll(filteredData)
                } else {
                    publications.addAll(auxPublications)
                }
            }
            requireActivity().runOnUiThread {
                publicationAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
           if (!query.isNullOrEmpty()) {
                searchByBreed(query.lowercase())
           }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }


}