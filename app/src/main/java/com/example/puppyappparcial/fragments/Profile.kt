package com.example.puppyappparcial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.puppyappparcial.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"


class Profile : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val imageView = view.findViewById<ImageView>(R.id.imageView2)
        val textView = view.findViewById<TextView>(R.id.textView)
        val editTextImageUrl = view.findViewById<EditText>(R.id.editTextImageUrl)
        val btnChangeImage = view.findViewById<Button>(R.id.btnChangeImage)

        val nombre = arguments?.getString("nombre")
        val imagenUrl = arguments?.getString("imagenUrl")

        // Verifica que los valores no sean nulos antes de intentar establecerlos
        if (!nombre.isNullOrEmpty()) {
            textView.text = nombre
        }

        if (!imagenUrl.isNullOrEmpty()) {

            Glide.with(requireContext())
                .load(imagenUrl)
                .into(imageView)
        }

        btnChangeImage.setOnClickListener {
            val imagenUrl = editTextImageUrl.text.toString()
            if (URLUtil.isValidUrl(imagenUrl)) {
                Glide.with(requireContext())
                    .load(imagenUrl)
                    .into(imageView)
                editTextImageUrl.text.clear()
            } else {
                Toast.makeText(requireContext(), "URL inválida", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Profile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}