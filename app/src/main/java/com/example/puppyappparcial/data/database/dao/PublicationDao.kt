package com.example.puppyappparcial.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.puppyappparcial.data.database.entities.PublicationEntity
import com.example.puppyappparcial.domain.models.Breed


@Dao
interface PublicationDao {
    @Query("Select * from PublicationTable")
    suspend fun getAllPublications(): List<PublicationEntity>

    @Query("Select * from PublicationTable where breed = :breed")
    suspend fun getPublication(breed: String): PublicationEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPublication(publication: PublicationEntity)

    @Query("Update PublicationTable set owner = :owner, adopted = 1 where id = :id")
    suspend fun updateOwner(owner: String, id: Int)
    @Query("Update PublicationTable set favorite = :favorite where id = :id")
    suspend fun updateFavourite(favorite: Boolean, id: Int)

}