package com.example.puppyappparcial.activities

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.puppyappparcial.R
import com.example.puppyappparcial.data.GetDogsUseCase
import com.example.puppyappparcial.data.network.GetSubBreedUseCase
import com.example.puppyappparcial.domain.Dog
import com.example.puppyappparcial.domain.SubBreed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val getDogsUseCase: GetDogsUseCase,
    private val getSubBreedUseCase: GetSubBreedUseCase
) : ViewModel() {

    val dogModel = MutableLiveData<Dog>()
    val subBreedModel = MutableLiveData<SubBreed>()

    fun onCreate(){
        viewModelScope.launch {
            val result = getDogsUseCase()

            if (result != null){
                dogModel.postValue(result)
            }
        }
    }

    fun getSubBreed(breed:String){
        viewModelScope.launch {
            val result = getSubBreedUseCase("$breed")

            if (result != null){
                subBreedModel.postValue(result)
            }
        }
    }
}