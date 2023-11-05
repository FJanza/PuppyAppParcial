package com.example.puppyappparcial.recycled_view

import android.view.View
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyappparcial.R
import com.example.puppyappparcial.databinding.ItemPerroBinding
import com.squareup.picasso.Picasso
class PerroHolder (v: View) : RecyclerView.ViewHolder(v) {

    private var view: View
    private var binding = ItemPerroBinding.bind(v)

    init {
        this.view = v
    }

        fun bind(image: String){
            Picasso.get().load(image).into(binding.dogImage)
        }

/*
    fun setName(name: String) {
        val txt: TextView = view.findViewById(R.id.txt_name_item)
        txt.text = name
    }

    fun setCurso(curso: String){
        val txt: TextView = view.findViewById(R.id.txtCurso)
        txt.text = curso
    }
*/
    fun getCardLayout (): CardView {
        return view.findViewById(R.id.ItemPerroBinding)
    }
/*
    fun setOrden(orden: Int){
        val txt: TextView = view.findViewById(R.id.txtOrden)
        txt.text = orden.toString()
    }
*/


//
//        fun getImageView () : ImageView {
//            return view.findViewById(R.id.img_item)
//        }
}