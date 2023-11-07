package com.example.puppyappparcial.recyclerViewPublications.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.puppyappparcial.R

class ImagePagerAdapter(
    private val imageUrls: List<String>
) : RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Puedes agregar vistas específicas si es necesario
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = imageUrls[position]
        // Carga la imagen utilizando Glide o la biblioteca que prefieras
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.itemView.findViewById(R.id.imageView)) // Reemplaza "imageView" con el ID correcto
    }

    override fun getItemCount(): Int {
        return imageUrls.size
    }
}
