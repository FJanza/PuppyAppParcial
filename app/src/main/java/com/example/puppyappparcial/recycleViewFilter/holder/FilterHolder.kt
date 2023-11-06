package com.example.puppyappparcial.recycleViewFilter.holder

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyappparcial.R
import com.example.puppyappparcial.databinding.ItemFilterBinding

class FilterHolder (view: View) : RecyclerView.ViewHolder(view) {

    private var view: View
    val checkBoxBreed = view.findViewById<CheckBox>(R.id.checkBoxBreed)
    val checkBoxSubBreed = view.findViewById<CheckBox>(R.id.checkBoxSubBreed)
    val checkBoxLocation = view.findViewById<CheckBox>(R.id.checkBoxLocation)

    init {
        this.view = view
        checkBoxBreed.setOnCheckedChangeListener { _, isChecked ->
            checkBoxBreed.isChecked = isChecked
        }
        checkBoxSubBreed.setOnCheckedChangeListener { _, isChecked ->
            checkBoxSubBreed.isChecked = isChecked
        }
        checkBoxLocation.setOnCheckedChangeListener { _, isChecked ->
            checkBoxLocation.isChecked = isChecked
        }

    }

    fun setBreed(newBreed: String){
        val breed: TextView = view.findViewById(R.id.filterBreed)
        breed.text = newBreed
    }

    fun setSubBreed(newSubBreed: String){
        val subBreed: TextView = view.findViewById(R.id.filterSubBreed)
        subBreed.text = newSubBreed
    }

    fun setLocation(newLocation: String){
        val location: TextView = view.findViewById(R.id.filterLocation)
        location.text = newLocation
    }



}