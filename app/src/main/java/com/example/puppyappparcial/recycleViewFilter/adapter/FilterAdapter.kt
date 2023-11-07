package com.example.puppyappparcial.recycleViewFilter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyappparcial.R
import com.example.puppyappparcial.domain.models.Publication
import com.example.puppyappparcial.recycleViewFilter.FilterModel
import com.example.puppyappparcial.recycleViewFilter.holder.FilterHolder

class FilterAdapter(
    private val filters: MutableList<FilterModel>
) : RecyclerView.Adapter<FilterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_filter, parent, false)

        return (FilterHolder(view))
    }

    override fun getItemCount() = filters.size

    override fun onBindViewHolder(holder: FilterHolder, position: Int) {
        val filter = filters[position]

        holder.setBreed(filter.breed)
        holder.checkBoxBreed.isChecked = filter.isChecked
        holder.setSubBreed(filter.subBreed)
        holder.checkBoxSubBreed.isChecked = filter.isChecked
        holder.setLocation(filter.location)
        holder.checkBoxLocation.isChecked = filter.isChecked
    }

}