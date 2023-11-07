package com.example.puppyappparcial.recyclerViewPublications.adapter

import android.util.Log
import com.example.puppyappparcial.domain.models.Publication
import com.example.puppyappparcial.recyclerViewPublications.holder.PublicationHolder
import com.example.puppyappparcial.recyclerViewPublications.listener.OnViewItemClickedListener

class FavoriteAdapter(
    var publications: MutableList<Publication>,
    val onItemClick: OnViewItemClickedListener
) : PublicationAdapter(publications, onItemClick){
    var favoritePublications: MutableList<Publication> = ArrayList()

    fun getFavoritePublication(): MutableList<Publication> {
        favoritePublications = publications.filter { it.favorite!! }.toMutableList()
        return favoritePublications
    }

    override fun onBindViewHolder(holder: PublicationHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (position < favoritePublications.size) {
            val favorite = favoritePublications[position].favorite

        }

    }
}