package com.example.puppyappparcial.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.puppyappparcial.R
import com.example.puppyappparcial.data.DogRepository
import com.example.puppyappparcial.data.database.entities.PublicationEntity
import com.example.puppyappparcial.domain.models.Publication
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailPublication  constructor(
) : Fragment() {
    var dogInformation: Publication? = null
    @Inject
    lateinit var repository: DogRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dogInformation = it.getSerializable("selectedPublication") as? Publication
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_publication, container, false)

        val nameTextView = view.findViewById<TextView>(R.id.nameTextView)
        val ageTextView = view.findViewById<TextView>(R.id.ageTextView)
        val locationTextView = view.findViewById<TextView>(R.id.locationTextView)
        val genderTextView = view.findViewById<TextView>(R.id.genderTextView)
        val weightTextView = view.findViewById<TextView>(R.id.weightTextView)
        val descriptionTextView = view.findViewById<TextView>(R.id.descriptionTextView)
        val ownerTextView = view.findViewById<TextView>(R.id.ownerTextView)
        val buttonToCall = view?.findViewById<ImageButton>(R.id.buttonToCall)
        val ownerImageView = view.findViewById<ImageView>(R.id.ownerImage)
        val dogPublicationImage = view.findViewById<ImageView>(R.id.dogPublicationImage)
        val adoptionButton = view.findViewById<Button>(R.id.adoptButton)



        if (dogInformation != null) {
            nameTextView.text =  "${dogInformation?.name}"
            ageTextView.text = "${dogInformation?.age}"
            locationTextView.text = "${dogInformation?.location}"
            genderTextView.text = "${dogInformation?.sex}"
            weightTextView.text = "${dogInformation?.weight}"
            descriptionTextView.text = "${dogInformation?.description}"
            ownerTextView.text = "${dogInformation?.owner}"

            val imageUrlOwner = dogInformation?.ownerImgUrl
            val imageUrlDog = dogInformation?.imgs


            if (imageUrlOwner != null) {
                Glide.with(requireContext())
                    .load(imageUrlOwner)
                    .into(ownerImageView)
            }
            if (imageUrlDog != null) {
                Glide.with(requireContext())
                    .load(imageUrlDog)
                    .into(dogPublicationImage)
            }
        }



        buttonToCall?.setOnClickListener {
            val phoneNumber = dogInformation?.ownerNumber.toString()
            if (phoneNumber.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:$phoneNumber")
                startActivity(intent)
            }
        }

        adoptionButton?.setOnClickListener {
            val ownerName = "test"

            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                repository.updateOwner(dogInformation?.id!!, ownerName)
            }
            Toast.makeText(requireContext(), "Has adoptado a ${dogInformation?.owner}", Toast.LENGTH_SHORT).show()

        }

        return view
    }
}