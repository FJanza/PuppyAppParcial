package com.example.puppyappparcial.activities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.puppyappparcial.domain.GetDogsUseCase
import com.example.puppyappparcial.domain.GetSubBreedUseCase
import com.example.puppyappparcial.domain.models.Dog
import com.example.puppyappparcial.domain.models.SubBreed
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