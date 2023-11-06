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
        Picasso.get().load(image).into(binding.dogImage)
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

    fun setFavorite(favorite: Boolean){
        val checked: ImageView = view.findViewById(R.id.favoriteChecked)
        val notChecked: ImageView = view.findViewById(R.id.favoriteNotChecked)
        if (favorite == true){
            checked.visibility = View.VISIBLE
            notChecked.visibility = View.GONE
        } else {
            notChecked.visibility = View.VISIBLE
            checked.visibility = View.GONE
        }
    }

    fun getCardLayout (): CardView {
        return view.findViewById(R.id.ItemPerroBinding)
    }
}