package com.example.puppyappparcial.recyclerView.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyappparcial.R
import com.example.puppyappparcial.recyclerView.listener.OnViewItemClickedListener
import com.example.puppyappparcial.recyclerView.Perro
import com.example.puppyappparcial.recyclerView.holder.PerroHolder

class PerroListAdapter(
    private val perroListo: MutableList<Perro>,
    private val onItemClick: OnViewItemClickedListener
) : RecyclerView.Adapter<PerroHolder>() {

    override fun getItemCount() = perroListo.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerroHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_perro,parent,false)

/*        val txtView = view.findViewById<TextView>(R.id.txtCurso)
        txtView.setText("Curso")*/
        return (PerroHolder(view))
    }

    override fun onBindViewHolder(holder: PerroHolder, position: Int) {

        val perro = perroListo[position]

//        holder.setName(TextUtils.concat(perro.nombre, " (", perro.edad.toString(), ")").toString())
//        holder.setCurso(perro.curso)
//        holder.setOrden(position)

        holder.getCardLayout().setOnClickListener{
            onItemClick.onViewItemDetail(perro)
        }
    }


}

