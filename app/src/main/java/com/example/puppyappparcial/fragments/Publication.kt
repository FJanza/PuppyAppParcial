package com.example.puppyappparcial.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.puppyappparcial.R
import com.example.puppyappparcial.data.DogRepository
import com.example.puppyappparcial.data.database.entities.PublicationEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Float.parseFloat
import java.lang.Integer.parseInt
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

@AndroidEntryPoint
class Publication : Fragment() {

    lateinit var buttonUpload: Button
    lateinit var inputName: EditText
    lateinit var inputAge: EditText
    lateinit var inputLocation: EditText
    lateinit var inputGender: EditText
    lateinit var inputWeigth: EditText
    lateinit var inputDescription: EditText
    lateinit var inputBreed: EditText
    lateinit var inputSubBreed: EditText
    lateinit var inputImageUrl: EditText
    lateinit var inputImageUrl2: EditText
    lateinit var inputImageUrl3: EditText
    lateinit var ownerName: String
    lateinit var ownerNumber: String
    lateinit var ownerImgUrl: String
    var isAllFieldsChecked = false
    @Inject
    lateinit var repository: DogRepository
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
        val view = inflater.inflate(R.layout.fragment_publication, container, false)
        super.onViewCreated(view, savedInstanceState)
        inputName = view.findViewById(R.id.inputName)
        inputAge = view.findViewById(R.id.inputAge)
        inputLocation = view.findViewById(R.id.inputLocation)
        inputGender = view.findViewById(R.id.inputGender)
        inputWeigth = view.findViewById(R.id.inputWeigth)
        inputDescription = view.findViewById(R.id.inputDescription)
        inputBreed = view.findViewById(R.id.inputBreed)
        inputSubBreed = view.findViewById(R.id.inputSubBreed)
        inputImageUrl = view.findViewById(R.id.inputImageUrl)
        inputImageUrl2 = view.findViewById(R.id.inputImageUrl2)
        inputImageUrl3 = view.findViewById(R.id.inputImageUrl3)
        buttonUpload = view.findViewById(R.id.buttonUpload)
        ownerName = arguments?.getString("nombre").toString()
        ownerImgUrl = arguments?.getString("imagenUrl").toString()
        ownerNumber = arguments?.getString("telefono").toString()

        buttonUpload.setOnClickListener {
            isAllFieldsChecked = checkAllFields()
            if (isAllFieldsChecked == true) {

                val publicationEntity = PublicationEntity(
                    breed = inputBreed.text.toString(),
                    subBreed = inputSubBreed.text.toString(),
                    name = inputName.text.toString(),
                    age = parseInt(inputAge.text.toString()),
                    sex = inputGender.text.toString(),
                    description = inputDescription.text.toString(),
                    weigth = parseFloat(inputWeigth.text.toString()),
                    location = inputLocation.text.toString(),
                    imgs = inputImageUrl.text.toString(),
                    owner = ownerName,
                    ownerImgUrl = ownerImgUrl,
                    ownerNumber = ownerNumber,
                    favorite = false,
                    adopted = false,
                )

                val scope = CoroutineScope(Dispatchers.IO)
                scope.launch {
                    repository.insertPublication(publicationEntity)
                }
                Toast.makeText(requireContext(), "Publicación creada!", Toast.LENGTH_SHORT).show()

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
        return true
    }

}