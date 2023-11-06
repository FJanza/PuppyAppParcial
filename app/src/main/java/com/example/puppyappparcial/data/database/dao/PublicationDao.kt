package com.example.puppyappparcial.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.puppyappparcial.data.database.entities.PublicationEntity


@Dao
interface PublicationDao {
    @Query("Select * from PublicationTable")
    suspend fun getAllPublications(): List<PublicationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPublication(publication: PublicationEntity)

    @Query("Update PublicationTable set owner = :owner where id = :id")
    suspend fun updateOwner(owner: String, id: Int)

    @Query("Update PublicationTable set favorite = not favorite where id = :id")
    suspend fun toggleFavorite(id: Int)
}