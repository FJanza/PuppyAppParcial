package com.example.puppyappparcial.recyclerViewPublications.listener
import com.example.puppyappparcial.domain.models.Publication
interface OnViewItemClickedListener {
    fun onViewItemDetail(publication: Publication)
}