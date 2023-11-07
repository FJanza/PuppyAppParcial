package com.example.puppyappparcial.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.puppyappparcial.data.database.dao.PublicationDao
import com.example.puppyappparcial.data.database.entities.PublicationEntity

@Database(entities = [PublicationEntity::class], version=3)
abstract class PublicationDataBase: RoomDatabase() {
    abstract fun getPublicationDao () : PublicationDao
}