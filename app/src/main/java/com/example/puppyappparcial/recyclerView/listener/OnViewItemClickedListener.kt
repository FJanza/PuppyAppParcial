package com.example.puppyappparcial.recyclerView.listener
import com.example.puppyappparcial.domain.models.Publication
interface OnViewItemClickedListener {
    fun onViewItemDetail(publication: Publication)
}