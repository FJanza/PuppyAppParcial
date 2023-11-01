package com.example.puppyappparcial.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.puppyappparcial.R


class ViewItem : Fragment() {

    lateinit var vista: View
    lateinit var info: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.view_item_info, container, false)
        info = vista.findViewById(R.id.txtInfo)
        return vista;
    }

    override fun onStart() {
        super.onStart()

        arguments?.let {
            val perro = ViewItemArgs.fromBundle(it).perro

            info.text = perro.nombre
        }
    }

}