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
import com.bumptech.glide.Glide
import com.example.puppyappparcial.R
import com.example.puppyappparcial.domain.models.Publication

class DetailPublication : Fragment() {
    private var dogInformation: Publication? = Publication(
        id = 1,
        breed = "Golden",
        subBreed =  "",
        name = "Patroclo",
        age = 10,
        sex = "Macho",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam suscipit lacus vel elementum mattis. Quisque vitae bibendum lacus. Suspendisse lacinia, orci egestas efficitur ultrices, risus risus sollicitudin erat, vel tempor ex sem vitae ipsum. Nunc viverra tortor eu felis sollicitudin fermentum. Nullam dictum dapibus nibh et auctor. Interdum et malesuada fames ac ante ipsum primis in faucibus. Praesent euismod egestas leo efficitur aliquam. Mauris mauris felis, dapibus sed ipsum sed, egestas bibendum orci. Nam mattis tristique est sed tempus. Aliquam et bibendum risus, a pharetra ante. Mauris dictum placerat vestibulum. Donec turpis lacus, cursus et justo sed, dignissim pellentesque mauris. Aliquam nec tellus tristique, congue lacus eget, faucibus nisl.",
        weigth = 15.10f,
        location = "Buenos Aires",
        imgs = "https://images.dog.ceo/breeds/cockapoo/Guri8.jpg",
        owner = "Matias Gutierrez",
        ownerImgUrl = "https://images.dog.ceo/breeds/retriever-curly/n02099429_121.jpg",
        ownerNumber = 1137636787,
        favorite = false,
        adopted = false,
        checked = false
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // arguments?.let {
        //            selectedDog = it.getSerializable("selectedDog") as? Dog
        //        }
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
            weightTextView.text = "${dogInformation?.weigth}"
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
            val ownerName = arguments?.getString("nombre")
            if (!ownerName.isNullOrEmpty()) {
                dogInformation?.owner = ownerName
                dogInformation?.adopted = true

            }
        }

        return view
    }
}