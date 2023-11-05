package com.example.puppyappparcial.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyappparcial.R
import com.example.puppyappparcial.domain.models.Publication
import com.example.puppyappparcial.recyclerView.listener.OnViewItemClickedListener
import com.example.puppyappparcial.recyclerView.adapter.PublicationAdapter
import com.google.android.material.snackbar.Snackbar

class Home : Fragment(), OnViewItemClickedListener {

    private lateinit var view: View
    private lateinit var recPerros : RecyclerView


    //TODO NO usar modelo perro, usar modelo publicacion de rooms
    var publications : MutableList<Publication> = ArrayList()

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var publicationAdapter: PublicationAdapter

    companion object {
        fun newInstance() = Home()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_home, container, false)

        recPerros = view.findViewById(R.id.rec_perros)

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

        recPerros.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        publicationAdapter = PublicationAdapter(publications, this)

        recPerros.layoutManager = linearLayoutManager
        recPerros.adapter = publicationAdapter
    }

    override fun onViewItemDetail(publication: com.example.puppyappparcial.domain.models.Publication) {
        //val action = listFragmentDirections.actionListFragmentToViewItem(perro)
        //this.findNavController().navigate(action)
        //Snackbar.make(view,perro.nombre, Snackbar.LENGTH_SHORT).show()
        Toast.makeText(context, "Detalle", Toast.LENGTH_SHORT).show()
    }


}