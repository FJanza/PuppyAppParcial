package com.example.puppyappparcial.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyappparcial.R
import com.example.puppyappparcial.data.DogRepository
import com.example.puppyappparcial.data.database.entities.PublicationEntity
import com.example.puppyappparcial.domain.models.Publication
import com.example.puppyappparcial.recyclerViewPublications.listener.OnViewItemClickedListener
import com.example.puppyappparcial.recyclerViewPublications.adapter.PublicationAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class Home  constructor(
    //private val getBreedsUseCase: GetBreedsUseCase,
    //private val  getSubBreedUseCase: GetSubBreedUseCase
) : Fragment(), OnViewItemClickedListener {

    private lateinit var view: View
    private lateinit var recPerros : RecyclerView
    var publications : MutableList<Publication> = ArrayList()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var publicationAdapter: PublicationAdapter
    @Inject
    lateinit var repository: DogRepository
    private lateinit var p1 : PublicationEntity
    private lateinit var p2 : PublicationEntity
    private lateinit var p3 : PublicationEntity
    private lateinit var p4 : PublicationEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_home, container, false)

        recPerros = view.findViewById(R.id.rec_home)

        return view
    }

    override fun onStart() {
        super.onStart()
        val filterNav = view.findViewById<TextView>(R.id.filter)

        addDefaultPublications()

        //Configuración Obligatoria
        requireActivity()

        recPerros.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        publicationAdapter = PublicationAdapter(publications, this)

        recPerros.layoutManager = linearLayoutManager
        recPerros.adapter = publicationAdapter

        //loadNavGraph()

//        filterNav.setOnClickListener{
//            val intent = Intent(requireContext(), MainActivity2::class.java)
//            intent.putExtra("FilterFragment", Filters::class.java)
//            startActivity(intent)
////            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
////            val filterFragment = Filters(getBreedsUseCase, getSubBreedUseCase)
////            fragmentTransaction.replace(R.id.fragment_container, filterFragment)
////            fragmentTransaction.addToBackStack(null)
////            fragmentTransaction.commit()
//            //navigateToMoreFilters()
//        }

    }

//    private fun navigateToMoreFilters() {
//
//        view.findNavController().navigate(R.id.action_home_to_filters)
//
//
//    }

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
            val filteredData = dataFromDB.filter { it.adopted == false }
            publications.clear()
            publications.addAll(filteredData)

            requireActivity().runOnUiThread {
                publicationAdapter.notifyDataSetChanged()
            }
        }
    }


}