package com.example.puppyappparcial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyappparcial.R
import com.example.puppyappparcial.recycled_view.OnViewItemClickedListener
import com.example.puppyappparcial.recycled_view.Perro
import com.example.puppyappparcial.recycled_view.PerroListAdapter
import com.google.android.material.snackbar.Snackbar

class listFragment : Fragment(), OnViewItemClickedListener {

    lateinit var v: View

    lateinit var recPerros : RecyclerView

    var perros : MutableList<Perro> = ArrayList()

    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var perroListAdapter: PerroListAdapter

    companion object {
        fun newInstance() = listFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v =  inflater.inflate(R.layout.fragment_home, container, false)


        recPerros = v.findViewById(R.id.rec_perros)

        return v
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
        //findNavController().navigate(action)
        Snackbar.make(v,perro.nombre,Snackbar.LENGTH_SHORT).show()
    }
}