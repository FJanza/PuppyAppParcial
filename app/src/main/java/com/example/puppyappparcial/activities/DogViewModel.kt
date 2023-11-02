package com.example.puppyappparcial.activities

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.puppyappparcial.R
import com.example.puppyappparcial.data.GetDogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val getDogsUseCase: GetDogsUseCase
) : ViewModel() {

    fun onCreate(){
        viewModelScope.launch {
            val result = getDogsUseCase()

//            if (!result.isNullOrEmpty()){
//                Log.d(String(), result.toString())
//            } else {
//                Log.d(String(), result.toString())
//            }
        }
    }
}