package com.example.puppyappparcial.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyappparcial.R
import com.example.puppyappparcial.domain.models.Publication
import com.example.puppyappparcial.recyclerViewPublications.adapter.FavoriteAdapter
import com.example.puppyappparcial.recyclerViewPublications.listener.OnViewItemClickedListener

class Favourites : Fragment(), OnViewItemClickedListener {

    private lateinit var view: View
    private lateinit var recycleFavourites: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var favoriteAdapter: FavoriteAdapter
    private var publications: MutableList<Publication> = ArrayList()
    private var favoritePublications: MutableList<Publication> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_favourites, container, false)

        recycleFavourites = view.findViewById(R.id.rec_favorites)

        return view
    }

    override fun onStart() {
        super.onStart()

        addDefaultPublications()

        //Configuración Obligatoria
        requireActivity()

        recycleFavourites.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        favoriteAdapter = FavoriteAdapter(publications, this)
        favoritePublications = favoriteAdapter.getFavoritePublication()
        favoriteAdapter.publications = favoritePublications

        recycleFavourites.layoutManager = linearLayoutManager
        recycleFavourites.adapter = favoriteAdapter
    }

    override fun onViewItemDetail(publication: Publication) {
        Toast.makeText(context, "Favoritos", Toast.LENGTH_SHORT).show()
    }

    private fun addDefaultPublications(){
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
            "",
            123123,
            true,
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
            "",
            123123,
            true,
            false,
            false
        ))
    }
}