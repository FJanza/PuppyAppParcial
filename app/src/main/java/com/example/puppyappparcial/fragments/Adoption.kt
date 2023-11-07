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
import com.example.puppyappparcial.recyclerViewPublications.adapter.AdoptionAdapter
import com.example.puppyappparcial.recyclerViewPublications.listener.OnViewItemClickedListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

@AndroidEntryPoint
class Adoption : Fragment(), OnViewItemClickedListener{

    private lateinit var view: View
    private lateinit var recycleAdoptions: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adoptionAdapter: AdoptionAdapter
    private var publications: MutableList<Publication> = ArrayList()
    private var adoptedPublications: MutableList<Publication> = ArrayList()
    @Inject
    lateinit var repository: DogRepository
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_adoption, container, false)

        recycleAdoptions = view.findViewById(R.id.rec_adoptions)

        return view
    }

    override fun onStart() {
        super.onStart()

        addDefaultPublications()

        //Configuración Obligatoria
        requireActivity()

        recycleAdoptions.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        adoptionAdapter = AdoptionAdapter(publications, this)
        adoptedPublications = adoptionAdapter.getAdoptedPublication()
        adoptionAdapter.publications = adoptedPublications

        recycleAdoptions.layoutManager = linearLayoutManager
        recycleAdoptions.adapter = adoptionAdapter
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
            val filteredData = dataFromDB.filter { it.adopted == true }
            publications.clear()
            publications.addAll(filteredData)

        }
    }

}