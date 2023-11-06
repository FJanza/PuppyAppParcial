package com.example.puppyappparcial.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.puppyappparcial.Dog
import com.example.puppyappparcial.R

class DetailPublication : Fragment() {
    private var dogInformation: Dog? = Dog(
        name = "Patroclo",
        age = 5,
        location = "Buenos Aires",
        gender = "Macho",
        weight = 10,
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam suscipit lacus vel elementum mattis. Quisque vitae bibendum lacus. Suspendisse lacinia, orci egestas efficitur ultrices, risus risus sollicitudin erat, vel tempor ex sem vitae ipsum. Nunc viverra tortor eu felis sollicitudin fermentum. Nullam dictum dapibus nibh et auctor. Interdum et malesuada fames ac ante ipsum primis in faucibus. Praesent euismod egestas leo efficitur aliquam. Mauris mauris felis, dapibus sed ipsum sed, egestas bibendum orci. Nam mattis tristique est sed tempus. Aliquam et bibendum risus, a pharetra ante. Mauris dictum placerat vestibulum. Donec turpis lacus, cursus et justo sed, dignissim pellentesque mauris. Aliquam nec tellus tristique, congue lacus eget, faucibus nisl.",
        imageUrl = listOf(
            "https://images.dog.ceo/breeds/cockapoo/Guri8.jpg",
            "URL2",
            "URL3"),
        imageUrlOwner = "https://images.dog.ceo/breeds/retriever-curly/n02099429_121.jpg",
        ownerName= "Matias",
        ownerNumber = 1137636787,
        isFaved = false,
        isAdopted = false
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
        val dogImageView = view.findViewById<ImageView>(R.id.ownerImage)

        if (dogInformation != null) {
            nameTextView.text =  "${dogInformation?.name}"
            ageTextView.text = "${dogInformation?.age}"
            locationTextView.text = "${dogInformation?.location}"
            genderTextView.text = "${dogInformation?.gender}"
            weightTextView.text = "${dogInformation?.weight}"
            descriptionTextView.text = "${dogInformation?.description}"
            ownerTextView.text = "${dogInformation?.ownerName}"

        }



        buttonToCall?.setOnClickListener {
            val phoneNumber = dogInformation?.ownerNumber.toString()
            if (phoneNumber.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:$phoneNumber")
                startActivity(intent)
            }
        }

        return view
    }
}