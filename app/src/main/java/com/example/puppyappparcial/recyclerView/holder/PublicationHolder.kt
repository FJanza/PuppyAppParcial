package com.example.puppyappparcial.recyclerView.holder

import android.view.View
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

    fun getCardLayout (): CardView {
        return view.findViewById(R.id.ItemPerroBinding)
    }
}