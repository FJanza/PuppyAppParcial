package com.example.puppyappparcial.recyclerView.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyappparcial.R
import com.example.puppyappparcial.domain.models.Publication
import com.example.puppyappparcial.recyclerView.listener.OnViewItemClickedListener
import com.example.puppyappparcial.recyclerView.holder.PublicationHolder

class PublicationAdapter(
    private val publications: MutableList<Publication>,
    private val onItemClick: OnViewItemClickedListener
) : RecyclerView.Adapter<PublicationHolder>() {

    override fun getItemCount() = publications.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicationHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_perro,parent,false)

/*        val txtView = view.findViewById<TextView>(R.id.txtCurso)
        txtView.setText("Curso")*/
        return (PublicationHolder(view))
    }

    override fun onBindViewHolder(holder: PublicationHolder, position: Int) {

        val publication = publications[position]
        val imgTest = publication.imgs

//        holder.setName(TextUtils.concat(perro.nombre, " (", perro.edad.toString(), ")").toString())
//        holder.setCurso(perro.curso)
//        holder.setOrden(position)

        holder.bind(imgTest)

        holder.getCardLayout().setOnClickListener{
            onItemClick.onViewItemDetail(publication)
        }
    }


}

