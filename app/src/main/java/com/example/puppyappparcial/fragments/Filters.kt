package com.example.puppyappparcial.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.puppyappparcial.R
import com.example.puppyappparcial.domain.GetBreedsUseCase
import com.example.puppyappparcial.domain.GetSubBreedUseCase
import com.example.puppyappparcial.domain.models.Publication
import com.example.puppyappparcial.recycleViewFilter.FilterModel
import com.example.puppyappparcial.recycleViewFilter.adapter.FilterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
@AndroidEntryPoint
class Filters @Inject constructor(
    private val getBreedsUseCase: GetBreedsUseCase,
    private val  getSubBreedUseCase: GetSubBreedUseCase
) : Fragment() {
    private lateinit var view: View
    private lateinit var recFilter: RecyclerView

    var filters : MutableList<FilterModel> = ArrayList()

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var filterAdapter: FilterAdapter
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
        view =  inflater.inflate(R.layout.fragment_filters, container, false)

        recFilter = view.findViewById(R.id.rec_filter)

        return view
    }

    override fun onStart() {
        super.onStart()
        getBreeds()

        requireActivity()

        recFilter.setHasFixedSize(false)
        linearLayoutManager = LinearLayoutManager(context)
        filterAdapter = FilterAdapter(filters)

        recFilter.layoutManager = linearLayoutManager
        recFilter.adapter = filterAdapter

    }

    private fun getBreeds(){
        CoroutineScope(Dispatchers.IO).launch {
            val breeds = getBreedsUseCase()
            if (breeds != null) {
                for (i in 1..breeds.message.size) {
                    val subBreeds = getSubBreedUseCase(breeds.message[i])
                    filters.add(FilterModel(breeds.message[i], subBreeds.message[i], "Bs As", false))
                }
            }
        }
    }
}