package com.example.puppyappparcial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.puppyappparcial.R

class Publication : Fragment() {

    lateinit var buttonUpload: Button
    lateinit var inputName: EditText
    lateinit var inputAge: EditText
    lateinit var inputLocation: EditText
    lateinit var inputGender: EditText
    lateinit var inputWeigth: EditText
    lateinit var inputDescription: EditText
    lateinit var inputImageUrl: EditText
    lateinit var inputImageUrl2: EditText
    lateinit var inputImageUrl3: EditText
    var isAllFieldsChecked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_publication, container, false)
        super.onViewCreated(view, savedInstanceState)
        inputName = view.findViewById(R.id.inputName)
        inputAge = view.findViewById(R.id.inputAge)
        inputLocation = view.findViewById(R.id.inputLocation)
        inputGender = view.findViewById(R.id.inputGender)
        inputWeigth = view.findViewById(R.id.inputWeigth)
        inputDescription = view.findViewById(R.id.inputDescription)
        inputImageUrl = view.findViewById(R.id.inputImageUrl)
        inputImageUrl2 = view.findViewById(R.id.inputImageUrl2)
        inputImageUrl3 = view.findViewById(R.id.inputImageUrl3)
        buttonUpload = view.findViewById(R.id.buttonUpload)

        buttonUpload.setOnClickListener {
            isAllFieldsChecked = checkAllFields()
            if (isAllFieldsChecked == true) {
               //AGREGAR METODO QUE AGREGUE EL PERRO A LA BASE DE DATOS
            } 
        }
        return view
    }

    private fun checkAllFields(): Boolean {
        if (inputName.text.toString().isNullOrBlank()) {
            inputName.error = "Ingrese un nombre valido"
            return false
        }
        if (inputAge.text.toString().isNullOrBlank()) {
            inputAge.error = "Ingrese una edad valida"
            return false
        }
        if (inputLocation.text.toString().isNullOrBlank()) {
            inputLocation.error = "Ingrese una ubicacion valida"
            return false
        }
        if (inputGender.text.toString().isNullOrBlank()) {
            inputGender.error = "Ingrese un genero valido"
            return false
        }
        if (inputWeigth.text.toString().isNullOrBlank()) {
            inputWeigth.error = "Ingrese un peso valido"
            return false
        }
        if (inputDescription.text.toString().isNullOrBlank()) {
            inputDescription.error = "Ingrese una descripcion valida"
            return false
        }
        if (inputImageUrl.text.toString().isNullOrBlank()) {
            inputImageUrl.error = "Ingrese una imagen valida"
            return false
        }
        if (inputImageUrl2.text.toString().isNullOrBlank()) {
            inputImageUrl2.error = "Ingrese una imagen valida"
            return false
        }
        if (inputImageUrl3.text.toString().isNullOrBlank()) {
            inputImageUrl3.error = "Ingrese una imagen valida"
            return false
        }

        return true
    }

}