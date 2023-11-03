package com.example.puppyappparcial.activities

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.puppyappparcial.R
import com.example.puppyappparcial.data.GetDogsUseCase
import com.example.puppyappparcial.domain.Dog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val getDogsUseCase: GetDogsUseCase
) : ViewModel() {

    val dogModel = MutableLiveData<Dog>()

    fun onCreate(){
        viewModelScope.launch {
            val result = getDogsUseCase()

            if (result != null){
                dogModel.postValue(result)
            }
        }
    }
}