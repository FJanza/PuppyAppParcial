package com.example.puppyappparcial.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.puppyappparcial.data.database.entities.PublicationEntity

@Dao
interface PublicationDao {
    @Query("Select * from PublicationTable")
    suspend fun getAllPublications(): List<PublicationEntity>
}