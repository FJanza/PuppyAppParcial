package com.example.puppyappparcial.recyclerViewPublications.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyappparcial.R
import com.example.puppyappparcial.databinding.ItemPerroBinding
import com.squareup.picasso.Picasso
class PublicationHolder (v: View) : RecyclerView.ViewHolder(v) {

    private var view: View
    private var binding = ItemPerroBinding.bind(v)

    init {
        this.view = v
    }

    fun bind(image: String){
        val imageUrls = image.split(",")
        Picasso.get().load(imageUrls[0]).into(binding.dogImage)
    }

    fun setName(name: String){
        val txt: TextView = view.findViewById(R.id.nombrePerro)
        txt.text = name
    }

    fun setBreed(breed: String){
        val txt: TextView = view.findViewById(R.id.raza)
        txt.text = breed
    }

    fun setSubBreed(subBreed: String){
        val txt: TextView = view.findViewById(R.id.subraza)
        txt.text = subBreed
    }

    fun setAge(age: Int){
        val txt: TextView = view.findViewById(R.id.edad)
        txt.text = age.toString()
    }

    fun setGender(gender: String){
        val txt: TextView = view.findViewById(R.id.genero)
        txt.text = gender
    }

    fun setFavIcon(favourite: Boolean){
        val favButton = view.findViewById<ImageView>(R.id.favButtonCheck)
        if (favourite){
            favButton.visibility = View.VISIBLE
        } else {
            favButton.visibility = View.GONE
        }
    }

    fun getCardLayout (): CardView {
        return view.findViewById(R.id.ItemPerroBinding)
    }
}