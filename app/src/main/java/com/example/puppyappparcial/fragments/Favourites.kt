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
import com.example.puppyappparcial.recyclerViewPublications.adapter.PublicationAdapter
import com.example.puppyappparcial.recyclerViewPublications.listener.OnViewItemClickedListener


class Favourites : Fragment(), OnViewItemClickedListener {

    private lateinit var view: View
    private lateinit var recycleFavourites: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var publicationAdapter: PublicationAdapter
    private var publications: MutableList<Publication> = ArrayList()

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

        for (i in 1..10){
            publications.add(com.example.puppyappparcial.domain.models.Publication(
                1,
                "Golden",
                "Retriever",
                "Perro",
                5,
                "Macho",
                "Buen Perro",
                30F,
                "Bs As",
                "https://images.dog.ceo/breeds/bouvier/n02106382_1365.jpg",
                "Yo",
                false,
                false
            ))
        }

        //Configuración Obligatoria
        requireActivity()

        recycleFavourites.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        publicationAdapter = PublicationAdapter(publications, this)

        recycleFavourites.layoutManager = linearLayoutManager
        recycleFavourites.adapter = publicationAdapter
    }

    override fun onViewItemDetail(publication: Publication) {
        Toast.makeText(context, "Favoritos", Toast.LENGTH_SHORT).show()
    }
}