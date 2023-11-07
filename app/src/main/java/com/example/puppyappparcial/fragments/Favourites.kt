package com.example.puppyappparcial.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyappparcial.R
import com.example.puppyappparcial.data.DogRepository
import com.example.puppyappparcial.domain.models.Publication
import com.example.puppyappparcial.recyclerViewPublications.adapter.FavoriteAdapter
import com.example.puppyappparcial.recyclerViewPublications.listener.OnViewItemClickedListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class Favourites : Fragment(), OnViewItemClickedListener {

    private lateinit var view: View
    private lateinit var recycleFavourites: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var favoriteAdapter: FavoriteAdapter
    private var publications: MutableList<Publication> = ArrayList()
    private var favoritePublications: MutableList<Publication> = ArrayList()
    @Inject
    lateinit var repository: DogRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_favourites, container, false)

        recycleFavourites = view.findViewById(R.id.rec_favorites)

        return view
    }

    override fun onStart() {
        super.onStart()

        addDefaultPublications()

        //Configuración Obligatoria
        requireActivity()

        recycleFavourites.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        favoriteAdapter = FavoriteAdapter(publications, this)
        favoritePublications = favoriteAdapter.getFavoritePublication()
        favoriteAdapter.publications = favoritePublications

        recycleFavourites.layoutManager = linearLayoutManager
        recycleFavourites.adapter = favoriteAdapter
    }

    override fun onViewItemDetail(publication: com.example.puppyappparcial.domain.models.Publication) {
        val detailPublicationFragment = DetailPublication()
        val args = Bundle()
        args.putSerializable("selectedPublication", publication)
        detailPublicationFragment.arguments = args

        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, detailPublicationFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun addDefaultPublications(){
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            var dataFromDB = repository.getAllPublicationsFromDataBase()
            val filteredData = dataFromDB.filter { it.adopted == false && it.favorite == true }
            publications.clear()
            publications.addAll(filteredData)

        }
    }
}