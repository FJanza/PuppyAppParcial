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
import androidx.viewpager2.widget.ViewPager2
import com.example.puppyappparcial.recyclerViewPublications.adapter.ImagePagerAdapter


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

@AndroidEntryPoint
class DetailPublication  constructor(
) : Fragment() {
    var dogInformation: Publication? = null
    @Inject
    lateinit var repository: DogRepository
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null
    lateinit var ownerName: String
    lateinit var ownerNumber: String
    lateinit var ownerImgUrl: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dogInformation = it.getSerializable("selectedPublication") as? Publication
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
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
        val adoptionButton = view.findViewById<Button>(R.id.adoptButton)
        ownerName = arguments?.getString("nombre").toString()
        ownerImgUrl = arguments?.getString("imagenUrl").toString()
        ownerNumber = arguments?.getString("telefono").toString()

        val dogPublicationImage = view.findViewById<ViewPager2>(R.id.dogPublicationImage)
        val imageUrls = dogInformation?.imgs.toString().split(",")
        val imageAdapter = ImagePagerAdapter(imageUrls)
        dogPublicationImage.adapter = imageAdapter

        val favButton = view.findViewById<ImageView>(R.id.favButton)


        if (dogInformation != null) {
            nameTextView.text =  "${dogInformation?.name}"
            ageTextView.text = "${dogInformation?.age}"
            locationTextView.text = "${dogInformation?.location}"
            genderTextView.text = "${dogInformation?.sex}"
            weightTextView.text = "${dogInformation?.weight}"
            descriptionTextView.text = "${dogInformation?.description}"
            ownerTextView.text = "${dogInformation?.owner}"
            val imageUrlOwner = dogInformation?.ownerImgUrl

            if (imageUrlOwner != null) {
                Glide.with(requireContext())
                    .load(imageUrlOwner)
                    .into(ownerImageView)
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

            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                repository.updateOwner(dogInformation?.id!!, ownerName)
            }
            Toast.makeText(requireContext(), "Has adoptado a ${dogInformation?.name}", Toast.LENGTH_SHORT).show()

        }

        if (dogInformation?.adopted == true){
            adoptionButton.visibility = View.GONE

        } else {
            adoptionButton.visibility = View.VISIBLE
        }

        if (dogInformation?.favorite == true){
            favButton.setImageResource(R.drawable.icon_favorite_checked)
        } else {
            favButton.setImageResource(R.drawable.icon_favorite_not_checked)
        }

        favButton?.setOnClickListener{
            dogInformation?.favorite = !dogInformation?.favorite!!
            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                if (dogInformation?.favorite == true){
                    favButton.setImageResource(R.drawable.icon_favorite_checked)
                } else {
                    favButton.setImageResource(R.drawable.icon_favorite_not_checked)
                }
                repository.updateFavourite(dogInformation?.id!!, dogInformation?.favorite!!)
            }
            if (dogInformation?.favorite == true){
                Toast.makeText(requireContext(), "Has añadido a ${dogInformation?.name} en favoritos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Has sacado a ${dogInformation?.name} de favoritos", Toast.LENGTH_SHORT).show()
            }

        }

        return view
    }
}