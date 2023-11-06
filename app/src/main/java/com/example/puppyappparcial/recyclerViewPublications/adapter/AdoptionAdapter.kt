package com.example.puppyappparcial.recyclerViewPublications.adapter

import com.example.puppyappparcial.domain.models.Publication
import com.example.puppyappparcial.recyclerViewPublications.holder.PublicationHolder
import com.example.puppyappparcial.recyclerViewPublications.listener.OnViewItemClickedListener

class AdoptionAdapter (
    var publications: MutableList<Publication>,
    val onItemClick: OnViewItemClickedListener
) : PublicationAdapter(publications, onItemClick){
    var adoptedPublications: MutableList<Publication> = ArrayList()

    fun getAdoptedPublication(): MutableList<Publication> {
        adoptedPublications = publications.filter { it.adopted }.toMutableList()
        return adoptedPublications
    }

    override fun onBindViewHolder(holder: PublicationHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val adopted = adoptedPublications[position].adopted

        if (adopted == true) {
            holder.setFavorite(adopted)
        }
    }

}