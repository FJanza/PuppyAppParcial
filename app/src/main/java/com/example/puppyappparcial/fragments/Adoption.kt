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
import com.example.puppyappparcial.recyclerViewPublications.adapter.AdoptionAdapter
import com.example.puppyappparcial.recyclerViewPublications.listener.OnViewItemClickedListener

class Adoption : Fragment(), OnViewItemClickedListener{

    private lateinit var view: View
    private lateinit var recycleAdoptions: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adoptionAdapter: AdoptionAdapter
    private var publications: MutableList<Publication> = ArrayList()
    private var adoptedPublications: MutableList<Publication> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_adoption, container, false)

        recycleAdoptions = view.findViewById(R.id.rec_adoptions)

        return view
    }

    override fun onStart() {
        super.onStart()

        addDefaultPublications()

        //Configuración Obligatoria
        requireActivity()

        recycleAdoptions.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        adoptionAdapter = AdoptionAdapter(publications, this)
        adoptedPublications = adoptionAdapter.getAdoptedPublication()
        adoptionAdapter.publications = adoptedPublications

        recycleAdoptions.layoutManager = linearLayoutManager
        recycleAdoptions.adapter = adoptionAdapter
    }

    override fun onViewItemDetail(publication: Publication) {
        Toast.makeText(context, "Adopciones", Toast.LENGTH_SHORT).show()
    }

    private fun addDefaultPublications(){
        publications.add(com.example.puppyappparcial.domain.models.Publication(
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
            false,
            true
        ))

        publications.add(com.example.puppyappparcial.domain.models.Publication(
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
            false,
            true
        ))
    }

}