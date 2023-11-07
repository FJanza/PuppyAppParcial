package com.example.puppyappparcial.recyclerViewPublications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyappparcial.R
import com.example.puppyappparcial.domain.models.Publication
import com.example.puppyappparcial.recyclerViewPublications.listener.OnViewItemClickedListener
import com.example.puppyappparcial.recyclerViewPublications.holder.PublicationHolder

open class PublicationAdapter(
    private var publications: MutableList<Publication>,
    private val onItemClick: OnViewItemClickedListener
) : RecyclerView.Adapter<PublicationHolder>(), Filterable {

    override fun getItemCount() = publications.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicationHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_perro,parent,false)

        /*        val txtView = view.findViewById<TextView>(R.id.txtCurso)
                txtView.setText("Curso")*/
        return (PublicationHolder(view))
    }

    override fun onBindViewHolder(holder: PublicationHolder, position: Int) {

        val publication = publications[position]

        if (publication != null){
            holder.bind(publication.imgs!!)
            holder.setName(publication.name!!)
            holder.setBreed(publication.breed!!)
            holder.setSubBreed(publication.subBreed!!)
            holder.setAge(publication.age!!)
            holder.setGender(publication.sex!!)
            holder.setFavIcon(publication.favorite!!)
        }

        holder.getCardLayout().setOnClickListener{
            onItemClick.onViewItemDetail(publication)
        }
    }

    override fun getFilter(): Filter {
        return  object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()
                val filteredList = mutableListOf<Publication>()

                if (constraint.isNullOrEmpty()){
                    filteredList.addAll(publications)
                } else {
                    val filterPattern = constraint.toString().toLowerCase().trim()

                    for (publication in publications) {
                        if (publication.breed!!.lowercase().contains(filterPattern)){
                            filteredList.add(publication)
                        }
                    }

                    if (filterPattern.isNotEmpty()) {
                        for (publication in publications) {
                            if (publication.subBreed != null && !publication.subBreed.toLowerCase().contains(filterPattern)){
                                filteredList.remove(publication)
                            }
                        }
                    }

//                    for (publication in publications){
//                        if (publication.location != null && publication.location.toLowerCase().contains(filterPattern))
//                            filteredList.add(publication)
//                    }
                }
                results.values = filteredList
                results.count = filteredList.size

                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                publications = results?.values as MutableList<Publication>
                notifyDataSetChanged()
            }
        }
    }


}