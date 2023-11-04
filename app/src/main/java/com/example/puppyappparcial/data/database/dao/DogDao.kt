package com.example.puppyappparcial.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.puppyappparcial.data.database.entities.DogEntity

@Dao
interface DogDao {
    @Query("Select * from DogTable")
    suspend fun getAllDogs(): List<DogEntity>
}