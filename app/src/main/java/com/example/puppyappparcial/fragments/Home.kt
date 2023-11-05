package com.example.puppyappparcial.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyappparcial.R
import com.example.puppyappparcial.recyclerView.listener.OnViewItemClickedListener
import com.example.puppyappparcial.recyclerView.Perro
import com.example.puppyappparcial.recyclerView.adapter.PerroListAdapter
import com.google.android.material.snackbar.Snackbar

class Home : Fragment(), OnViewItemClickedListener {

    private lateinit var view: View
    private lateinit var recPerros : RecyclerView


    //TODO NO usar modelo perro, usar modelo publicacion de rooms
    var perros : MutableList<Perro> = ArrayList()

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var perroListAdapter: PerroListAdapter

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

        //Creo la Lista Dinamica
        for (i in 1..10) {
            perros.add(Perro("Pedro",26, Perro.Constants.cursoA, ""))
            perros.add(Perro("Rodolfo",30, Perro.Constants.cursoA, ""))
            perros.add(Perro("Emilio",28, Perro.Constants.cursoB, ""))
            perros.add(Perro("Luis",37, Perro.Constants.cursoB, ""))
            perros.add(Perro("Carlos", 42, Perro.Constants.cursoC, ""))
            perros.add(Perro("David",21, Perro.Constants.cursoC, ""))
        }

        //Configuración Obligatoria
        requireActivity()

        recPerros.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        perroListAdapter = PerroListAdapter(perros, this)

        recPerros.layoutManager = linearLayoutManager
        recPerros.adapter = perroListAdapter
    }

    override fun onViewItemDetail(perro: Perro) {
        val action = listFragmentDirections.actionListFragmentToViewItem(perro)
        this.findNavController().navigate(action)
        Snackbar.make(view,perro.nombre, Snackbar.LENGTH_SHORT).show()
    }


}