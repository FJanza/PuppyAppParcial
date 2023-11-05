package com.example.puppyappparcial.activities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.puppyappparcial.domain.GetBreedsUseCase
import com.example.puppyappparcial.domain.GetSubBreedUseCase
import com.example.puppyappparcial.domain.models.Breed
import com.example.puppyappparcial.domain.models.SubBreed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val getBreedsUseCase: GetBreedsUseCase,
    private val getSubBreedUseCase: GetSubBreedUseCase
) : ViewModel() {

    val breedModel = MutableLiveData<Breed>()
    val subBreedModel = MutableLiveData<SubBreed>()

    fun onCreate(){
        viewModelScope.launch {
            val result = getBreedsUseCase()

            if (result != null){
                breedModel.postValue(result)
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